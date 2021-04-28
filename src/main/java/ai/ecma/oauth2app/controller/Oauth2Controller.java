package ai.ecma.oauth2app.controller;

import ai.ecma.oauth2app.enums.AuthTypeEnum;
import ai.ecma.oauth2app.payload.ApiResponse;
import ai.ecma.oauth2app.payload.UserDto;
import ai.ecma.oauth2app.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

/**
 * created by Baxromjon
 */

@RestController
@RequestMapping("/api")
public class Oauth2Controller {
    @Autowired
    Oauth2Service oauth2Service;

    @GetMapping("/oauth")
    public HttpEntity<?> registerOrLoginOauth(@AuthenticationPrincipal OAuth2User oAuth2User,
                                              @RequestParam AuthTypeEnum authTypeEnum) {

        ApiResponse apiResponse = oauth2Service.registerOrLoginOauth(oAuth2User, authTypeEnum);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody UserDto userDto) {
        ApiResponse apiResponse = oauth2Service.register(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

}
