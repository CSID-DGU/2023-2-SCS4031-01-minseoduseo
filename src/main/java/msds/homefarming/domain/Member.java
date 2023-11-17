package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String username;
    private String nickname;

    //==네이버 리프레시 토큰==//
    private String refreshToken;

    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }
    //====//

    protected Member(String image, String username, String nickname)
    {
        this.image = image;
        this.username = username;
        this.nickname = nickname;
    }

    //여기 수정함 오류 안 나는지 체크!!
    protected Member()
    {
    }

    public static Member create(String image, String username, String nickname)
    {
        return new Member(image, username, nickname);
    }

    public Member update(String image, String nickname)
    {
        this.image = image;
        this.nickname = nickname;
        return this;
    }
}
