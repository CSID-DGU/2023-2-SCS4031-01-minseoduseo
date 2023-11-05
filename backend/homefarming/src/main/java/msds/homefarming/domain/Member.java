package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String username;
    private String nickname;

    protected Member(String image, String username, String nickname)
    {
        this.image = image;
        this.username = username;
        this.nickname = nickname;
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
