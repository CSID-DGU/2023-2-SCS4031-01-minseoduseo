package msds.homefarming.domain.dto.memberPlantDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class RegisterPlantResponseDto
{
    private Long id;
    private String image;
    private String name;
    private String nickname;
    private LocalDateTime creatDate;
    private String owner;
}
