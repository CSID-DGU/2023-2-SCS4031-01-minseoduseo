package msds.homefarming.domain.dto.memberPlantDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class RegisterPlantRequestDto
{
    private String image;
    private String name;
    private String nickname;
    private LocalDateTime createDate;
}
