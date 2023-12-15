package msds.homefarming.domain.dto.diaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeleteDiaryResponseDto
{
    private Boolean status;
    private String message;
}
