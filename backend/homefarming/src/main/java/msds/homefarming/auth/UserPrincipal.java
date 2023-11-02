package msds.homefarming.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserPrincipal
{
    private Long id;
    private String username;
    private String nickname;

}
