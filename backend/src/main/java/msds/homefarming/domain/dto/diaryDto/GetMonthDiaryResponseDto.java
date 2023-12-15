package msds.homefarming.domain.dto.diaryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class GetMonthDiaryResponseDto
{
    private int year;
    private int month;
    private int count;
    private String author;
    private List<GetSingleDiaryResponseDto> diaries = new ArrayList<>();
}
