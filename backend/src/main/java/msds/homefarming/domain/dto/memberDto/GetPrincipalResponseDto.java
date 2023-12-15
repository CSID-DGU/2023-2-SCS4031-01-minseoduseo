package msds.homefarming.domain.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetPrincipalResponseDto
{
    private Long id;
    private String image;
    private String username;
    private String nickname;
}
