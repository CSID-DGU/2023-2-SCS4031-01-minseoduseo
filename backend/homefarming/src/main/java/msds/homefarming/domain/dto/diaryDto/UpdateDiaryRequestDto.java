package msds.homefarming.domain.dto.diaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UpdateDiaryRequestDto
{
    private LocalDateTime modifyDate;
    private String title;
    private String plantName;
    private String contents;
}
