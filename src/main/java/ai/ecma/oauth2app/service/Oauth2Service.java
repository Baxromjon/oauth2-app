package ai.ecma.oauth2app.service;

import ai.ecma.oauth2app.entity.User;
import ai.ecma.oauth2app.enums.AuthTypeEnum;
import ai.ecma.oauth2app.payload.ApiResponse;
import ai.ecma.oauth2app.payload.UserDto;
import ai.ecma.oauth2app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * created by Baxromjon
 */

@Service
public class Oauth2Service {
    final//@Autowired o`rniga
            UserRepository userRepository;

    public Oauth2Service(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse registerOrLoginOauth(OAuth2User oAuth2User, AuthTypeEnum authTypeEnum) {
        UserDto userDtoByAuthType = getUserDtoByAuthType(oAuth2User, authTypeEnum);

        User user = userRepository.findByEMail(userDtoByAuthType != null ? userDtoByAuthType.getEMail() :
                UUID.randomUUID().toString()).orElse(new User());

        if (user.getName() == null)
            user.setName(userDtoByAuthType.getFirstName());
         if (user.getEmail() == null)
            user.setEmail(userDtoByAuthType.getEMail());
         userRepository.save(user);

        return new ApiResponse(
                "welcome",
                true,
                "token"
        );
    }

    private UserDto getUserFromOauth2Google(OAuth2User oAuth2User) {
        String email = Objects.requireNonNull(oAuth2User.getAttribute("email")).toString();
        String firstName = Objects.requireNonNull(oAuth2User.getAttribute("given_name")).toString();
        String lastName = Objects.requireNonNull(oAuth2User.getAttribute("family_name")).toString();
        String photoUrl = Objects.requireNonNull(oAuth2User.getAttribute("picture")).toString();
        return new UserDto(firstName,
                lastName,
                email,
                photoUrl);

    }

    private UserDto getUserDtoByAuthType(OAuth2User oAuth2User, AuthTypeEnum authTypeEnum) {
        switch (authTypeEnum) {
            case GOOGLE:
                return getUserFromOauth2Google(oAuth2User);
            default:
                return null;
        }
    }


    public ApiResponse register(UserDto userDto) {
    
        return null;
    }
}
