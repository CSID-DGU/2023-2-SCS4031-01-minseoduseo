package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class MemberPlant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    //==식물 색깔==//
    private String color;
    private String name;
    private String nickname;
    private LocalDateTime createDate;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    //==식물색깔 추가==//
    protected MemberPlant(String image, String color, String name, String nickname, LocalDateTime createDate)
    {
        this.image = image;
        this.color = color;
        this.name = name;
        this.nickname = nickname;
        this.createDate = createDate;

    }
    //==식물색깔 추가 끝==//

    protected MemberPlant()
    {
    }

    //==식물색깔 추가 ==//
    public static MemberPlant create(String image, String color, String name, String nickname, LocalDateTime createDate)
    {
        return new MemberPlant(image, color, name, nickname, createDate);
    }
    //==식물색깔 추가 끝==//

    //==식물색깔 추가==//
    public MemberPlant update(String image, String color, String name, String nickname, LocalDateTime createDate)
    {
        this.image = image;
        this.color = color;
        this.name = name;
        this.nickname = nickname;
        this.createDate = createDate;
        return this;
    }
    //==식물색깔 추가 끝==//
}
