package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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

    protected MemberPlant(String image, String name, String nickname, LocalDateTime createDate)
    {
        this.image = image;
        this.name = name;
        this.nickname = nickname;
        this.createDate = createDate;
    }

    public static MemberPlant create(String image, String name, String nickname, LocalDateTime createDate)
    {
        return new MemberPlant(image, name, nickname, createDate);
    }

    public MemberPlant update(String image, String name, String nickname, LocalDateTime createDate)
    {
        this.image = image;
        this.name = name;
        this.nickname = nickname;
        this.createDate = createDate;
        return this;
    }
}
