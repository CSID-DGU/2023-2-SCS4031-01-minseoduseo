package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member
{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username; //kakao_123456789
    private String nickname; // kakao_nickname
    private String profileImage;

    public Member(String username, String nickname, String profileImage)
    {
        this.username = username;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
