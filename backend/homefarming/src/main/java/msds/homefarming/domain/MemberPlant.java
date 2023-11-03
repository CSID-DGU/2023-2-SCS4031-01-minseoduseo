package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MemberPlant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private String nickname;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    public MemberPlant(String image, String name, String nickname, LocalDateTime createDate)
    {
        this.image = image;
        this.name = name;
        this.nickname = nickname;
        this.createDate = createDate;
    }
}
