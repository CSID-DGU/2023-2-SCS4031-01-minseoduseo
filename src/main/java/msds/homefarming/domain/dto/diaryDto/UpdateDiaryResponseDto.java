package msds.homefarming.domain.dto.diaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UpdateDiaryResponseDto
{
    private Long id;
    private LocalDateTime createDate;
    private String title;
    private String author;
    //==식물색==//
    private String color;
    private String plantName;
    private String contents;
}
