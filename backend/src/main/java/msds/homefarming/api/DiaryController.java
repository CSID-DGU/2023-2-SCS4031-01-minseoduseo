package msds.homefarming.api;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.dto.diaryDto.*;
import msds.homefarming.service.DiaryService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class DiaryController
{
    private final DiaryService diaryService;
    private final UserPrincipal userPrincipal;

    //==일기저장==//
    @PostMapping("/api/member/diary")
    public SaveDiaryResponseDto registerDiary(
            @RequestBody SaveDiaryRequestDto requestDto)
    {
        return diaryService.register(userPrincipal.getId(), requestDto);
    }

    //==일기상세조회(아이디)==//
    //== 색깔 수정 ==//
    @GetMapping("/api/member/diary/detail")
    public GetSingleDiaryResponseDto getDiary(
            @RequestParam("diaryId") Long diaryId)
    {
        return diaryService.findOne(userPrincipal.getId(), diaryId);
    }

    //==일기 월별 조회(년도,월)==//
    @GetMapping("/api/member/diary/monthly")
    public GetMonthDiaryResponseDto getMonthDiary(
            @RequestParam("year")int year,
            @RequestParam("month")int month
    )
    {
        return diaryService.findByMonth(year, month);
    }

    //==일기 날짜별 조회(년도, 월, 일)==//
    @GetMapping("/api/member/diary/daily")
    public GetDateDiaryResponseDto getDateDiary(
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day)
    {
        return diaryService.findByDate(year, month, day);
    }


    //==일기 업데이트==//
    @PutMapping("/api/member/diary")
    public UpdateDiaryResponseDto update(
            @RequestParam("diaryId") Long diaryId,
            @RequestBody UpdateDiaryRequestDto requestDto)
    {
        return diaryService.update(diaryId, requestDto);
    }

    //==일기삭제==//
    @DeleteMapping("/api/member/diary")
    public DeleteDiaryResponseDto delete(@RequestParam("diaryId")Long diaryId)
    {
        return diaryService.delete(diaryId);
    }
}
