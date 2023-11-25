package msds.homefarming.domain.dto.chatbotDto;

import lombok.Data;

import java.util.List;

@Data
public class DialogListDto
{
    private Long count;
    private Long memberId;
    private String memberName;
    private List<SingleDialogDto> history;
}
