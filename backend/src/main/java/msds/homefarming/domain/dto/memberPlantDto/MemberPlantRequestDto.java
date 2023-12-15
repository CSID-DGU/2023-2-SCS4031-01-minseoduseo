package msds.homefarming.domain.dto.memberPlantDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberPlantRequestDto
{
    private String image;
    //==식물색 추가==//
    private String color;
    private String name;
    private String nickname;
    private LocalDateTime createDate;

    //==식물색 추가==//
    public MemberPlantRequestDto(String image, String color, String name, String nickname, LocalDateTime createDate)
    {
        this.image = image;
        this.color = color;
        this.name = name;
        this.nickname = nickname;
        this.createDate = createDate;
    }
}
