package msds.homefarming.domain.dto.memberPlantDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class RegisterPlantRequestDto
{
    private String image;
    //==식물색깔 추가==//
    private String color;
    private String name;
    private String nickname;
    private LocalDateTime createDate;
}
