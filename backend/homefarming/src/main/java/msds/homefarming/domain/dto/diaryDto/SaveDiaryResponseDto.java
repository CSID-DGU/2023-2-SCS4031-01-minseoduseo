package msds.homefarming.domain.dto.diaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class SaveDiaryResponseDto
{
    private Long id;
    private LocalDateTime createDate;
    private String title;
    private String author;
    private String plantName;
    private String contents;
}
