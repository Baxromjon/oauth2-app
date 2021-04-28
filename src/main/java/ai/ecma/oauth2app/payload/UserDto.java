package ai.ecma.oauth2app.payload;

import ai.ecma.oauth2app.entity.Attachment;
import ai.ecma.oauth2app.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * created by Baxromjon
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;
    
   private String eMail;

    private String password;
    
    private String photoUrl;

    private Integer photoId;

    public UserDto(String firstName, String lastName, String eMail, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.photoUrl = photoUrl;
    }
}
