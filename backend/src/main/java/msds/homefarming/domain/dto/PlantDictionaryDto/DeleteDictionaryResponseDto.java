package msds.homefarming.domain.dto.PlantDictionaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeleteDictionaryResponseDto
{
    private Boolean status;
    private String message;
}
