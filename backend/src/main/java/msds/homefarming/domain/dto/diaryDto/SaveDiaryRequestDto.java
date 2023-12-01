package msds.homefarming.domain.dto.diaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class SaveDiaryRequestDto
{
    private LocalDateTime createDate;
    private String title;
    private String plantName;
    private String contents;
}
