package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Diary;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.dto.diaryDto.*;
import msds.homefarming.exception.NoAuthorityException;
import msds.homefarming.exception.NoExistDiaryException;
import msds.homefarming.repository.DiaryRepository;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DiaryService
{
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

        return new SaveDiaryResponseDto(
                diary.getId(),
                diary.getCreateDate(),
                diary.getTitle(),
                diary.getAuthor().getNickname(),
                diary.getPlantName(),
                diary.getContents());
    }

    //==일기 단건 조회==//
    public GetSingleDiaryResponseDto findOne(Long diaryId)
    {
        Diary diary = diaryRepository.findById(diaryId);
        if (diary == null)
        {
            throw new NoExistDiaryException("존재하지 않는 일지입니다.");
        }
        if (!diary.getAuthor().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 일기를 조회할 권한이 없습니다.");
        }
        return new GetSingleDiaryResponseDto(
                diary.getId(),
                diary.getCreateDate(),
                diary.getTitle(),
                diary.getAuthor().getNickname(),
                diary.getPlantName(),
                diary.getContents());
    }

    //==일기 업데이트==//
    @Transactional
    public UpdateDiaryResponseDto update(Long diaryId, UpdateDiaryRequestDto requestDto)
    {
        Diary diary = diaryRepository.findById(diaryId);
        if (diary == null)
        {
            throw new NoExistDiaryException("해당 일기가 존재하지 않습니다.");
        }
        if (!diary.getAuthor().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 일기를 수정할 권한이 없습니다.");
        }
        diary.update(
                requestDto.getTitle(),
                requestDto.getPlantName(),
                requestDto.getModifyDate(),
                requestDto.getContents());

        return new UpdateDiaryResponseDto(
                diary.getId(),
                diary.getCreateDate(),
                diary.getTitle(),
                diary.getAuthor().getNickname(),
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
