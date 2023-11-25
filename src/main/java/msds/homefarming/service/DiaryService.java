package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Diary;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.dto.diaryDto.*;
import msds.homefarming.exception.NoAuthorityException;
import msds.homefarming.exception.NoExistDiaryException;
import msds.homefarming.repository.DiaryRepository;
import msds.homefarming.repository.MemberPlantRepository;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DiaryService
{
    private final MemberPlantRepository memberPlantRepository;
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;
    private final UserPrincipal userPrincipal;

    //==일기저장==//
    @Transactional
    public SaveDiaryResponseDto register(Long memberId, SaveDiaryRequestDto requestDto)
    {
        Diary createDiary = Diary.create(
                requestDto.getTitle(),
                requestDto.getPlantName(),
                requestDto.getCreateDate(),
                requestDto.getContents());

        Diary diary = diaryRepository.save(createDiary);
        Member member = memberRepository.findById(memberId);
        diary.setAuthor(member);
        String plantColor = memberPlantRepository
                .findColor(memberId, requestDto.getPlantName());

        return new SaveDiaryResponseDto(
                diary.getId(),
                diary.getCreateDate(),
                diary.getTitle(),
                diary.getAuthor().getNickname(),
                plantColor,
                diary.getPlantName(),
                diary.getContents());
    }

    //==일기 단건 조회==//
    public GetSingleDiaryResponseDto findOne(Long memberId, Long diaryId)
    {
        Diary diary = diaryRepository.findById(diaryId);

        if (diary == null)
        {
            throw new NoExistDiaryException("존재하지 않는 일지입니다.");
        }
        if (!diary.getAuthor().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 일지를 조회할 권한이 없습니다.");
        }

        //==색깔 추가==//
        String plantColor = memberPlantRepository.findColor(memberId,diary.getPlantName());
        //====//

        //==색추가==//
        return new GetSingleDiaryResponseDto(
                diary.getId(),
                diary.getCreateDate(),
                diary.getTitle(),
                diary.getAuthor().getNickname(),
                plantColor,
                diary.getPlantName(),
                diary.getContents());
        //====//
    }

    //==일기 월별 조회==//
    public GetMonthDiaryResponseDto findByMonth(int year, int month)
    {
        Member principal = memberRepository.findById(userPrincipal.getId());
        List<Diary> diaries = diaryRepository.findByMonth(principal, year, month);
        List<GetSingleDiaryResponseDto> diaryList = new ArrayList<>();
        int count = 0;
        for (Diary diary : diaries)
        {
            //==색깔 추가==//
            String plantColor = memberPlantRepository
                    .findColor(principal.getId(), diary.getPlantName());
            GetSingleDiaryResponseDto diaryDto =
                    new GetSingleDiaryResponseDto(
                            diary.getId(),
                            diary.getCreateDate(),
                            diary.getTitle(),
                            diary.getAuthor().getNickname(),
                            plantColor,
                            diary.getPlantName(),
                            diary.getContents());
            diaryList.add(diaryDto);
            count++;
            //====//
        }
        return new GetMonthDiaryResponseDto(
                year,
                month,
                count,
                userPrincipal.getNickname(),
                diaryList);
    }

    //==일기 날짜별 조회==//
    public GetDateDiaryResponseDto findByDate(int year, int month, int day)
    {
        Member principal = memberRepository.findById(userPrincipal.getId());
        List<Diary> diaries = diaryRepository.findByDate(principal, year, month, day);
        List<GetSingleDiaryResponseDto> diaryList = new ArrayList<>();
        int count = 0;
        for (Diary diary : diaries)
        {
            //==색깔 추가==//
            String plantColor = memberPlantRepository
                    .findColor(principal.getId(), diary.getPlantName());

            GetSingleDiaryResponseDto diaryDto =
                    new GetSingleDiaryResponseDto(
                            diary.getId(),
                            diary.getCreateDate(),
                            diary.getTitle(),
                            diary.getAuthor().getNickname(),
                            plantColor,
                            diary.getPlantName(),
                            diary.getContents());
            diaryList.add(diaryDto);
            count++;
            //====//
        }

        return new GetDateDiaryResponseDto(
                LocalDate.of(year, month, day)
                , count
                , userPrincipal.getNickname()
                , diaryList);
    }

    //==일기 업데이트==//
    @Transactional
    public UpdateDiaryResponseDto update(Long diaryId, UpdateDiaryRequestDto requestDto)
    {
        Diary diary = diaryRepository.findById(diaryId);

        if (diary == null)
        {
            throw new NoExistDiaryException("해당 일지가 존재하지 않습니다.");
        }
        if (!diary.getAuthor().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 일지를 수정할 권한이 없습니다.");
        }
        diary.update(
                requestDto.getTitle(),
                requestDto.getPlantName(),
                requestDto.getModifyDate(),
                requestDto.getContents());

        //==식물색 추가==//
        String plantColor = memberPlantRepository
                .findColor(userPrincipal.getId(), requestDto.getPlantName());
        //====//

        return new UpdateDiaryResponseDto(
                diary.getId(),
                diary.getCreateDate(),
                diary.getTitle(),
                diary.getAuthor().getNickname(),
                //==식물 색==//
                plantColor,
                diary.getPlantName(),
                diary.getContents());
    }

    //==일기삭제==//
    @Transactional
    public DeleteDiaryResponseDto delete(Long diaryId)
    {
        Diary diary = diaryRepository.findById(diaryId);
        if (diary == null)
        {
            throw new NoExistDiaryException("해당 일지가 존재하지 않습니다.");
        }
        if (!diary.getAuthor().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 일지를 삭제할 권한이 없습니다.");
        }
        Boolean status = diaryRepository.deleteById(diaryId);
        return new DeleteDiaryResponseDto(status, "일지 삭제가 완료되었습니다.");
    }
}
