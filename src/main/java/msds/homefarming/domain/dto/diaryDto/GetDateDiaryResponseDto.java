package msds.homefarming.domain.dto.diaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import msds.homefarming.domain.Diary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class GetDateDiaryResponseDto
{
    private LocalDate createDate;
    private int count;
    private String author;
    private List<GetSingleDiaryResponseDto> diaries = new ArrayList<>();
}
