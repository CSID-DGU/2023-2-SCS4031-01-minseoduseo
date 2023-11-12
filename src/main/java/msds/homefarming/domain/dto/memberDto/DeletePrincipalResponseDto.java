package msds.homefarming.domain.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePrincipalResponseDto
{
    private Boolean status;
    private String message;
}
