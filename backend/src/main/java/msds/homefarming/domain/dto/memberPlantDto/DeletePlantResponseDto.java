package msds.homefarming.domain.dto.memberPlantDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePlantResponseDto
{
    private Boolean status;
    private String message;
}
