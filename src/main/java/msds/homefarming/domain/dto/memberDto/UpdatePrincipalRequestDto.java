package msds.homefarming.domain.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdatePrincipalRequestDto
{
    private String image;
    private String nickname;
}
