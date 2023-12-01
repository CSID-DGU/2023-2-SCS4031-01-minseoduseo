package msds.homefarming.auth;

import lombok.Getter;
import lombok.Setter;
import msds.homefarming.domain.Member;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserPrincipal
{
    private Long id;
    private String image;
    private String username;
    private String nickname;

    public void setPrincipal(Member member)
    {
        this.id = member.getId();
        this.image = member.getImage();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
    }
}
