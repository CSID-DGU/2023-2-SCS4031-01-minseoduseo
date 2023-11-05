package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.Diary;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class DiaryRepository
{
    private final EntityManager entityManager;

    //==일기저장==/
    public Diary save(Diary diary)
    {
        entityManager.persist(diary);
        return diary;
    }

    //==일기 단건 조회==/
    public Diary findById(Long diaryId)
    {
        return entityManager.find(Diary.class, diaryId);
    }

    //==월별 일기 리스트 조회==//
//    public List<Diary> findByMemberIdAndMonth(Long memberId, Year year, Month month)
//    {
//
//    }

    //==일기삭제==//
    public Boolean deleteById(Long diaryId)
    {
        Diary diary = entityManager.find(Diary.class, diaryId);
        entityManager.remove(diary);
        return true;
    }
}
