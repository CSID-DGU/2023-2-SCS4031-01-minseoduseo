package msds.homefarming.domain.dto.memberPlantDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class RegisterPlantResponseDto
{
    //==식물색깔 추가==//
    private Long id;
    private String image;
    private String color;
    private String name;
    private String nickname;
    private LocalDateTime creatDate;
    private String owner;
}
