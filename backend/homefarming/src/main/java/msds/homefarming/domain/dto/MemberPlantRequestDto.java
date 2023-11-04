package msds.homefarming.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberPlantRequestDto
{
    private String image;
    private String name;
    private String nickname;
    private LocalDateTime createDate;

    public MemberPlantRequestDto(String image, String name, String nickname, LocalDateTime createDate)
    {
        this.image = image;
        this.name = name;
        this.nickname = nickname;
        this.createDate = createDate;
    }
}
