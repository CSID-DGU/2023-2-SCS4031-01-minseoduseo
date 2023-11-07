package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.Diary;
import msds.homefarming.domain.Member;
import org.springframework.stereotype.Repository;

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

    //==일기 단건 조회==//
    public Diary findById(Long diaryId)
    {
        return entityManager.find(Diary.class, diaryId);
    }

    //==월별 일기 조회==//
    public List<Diary> findByMonth(Member member, int year, int month)
    {
        String jpql = "select d from Diary d " +
                "where d.author =: author " +
                "and d.createYear = :year " +
                "and d.createMonth =: month " +
                "order by d.createDate desc";

        return entityManager.createQuery(jpql, Diary.class)
                .setParameter("author", member)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList();
    }
    
    //==일별 일기 조회==//
    public List<Diary> findByDate(Member member, int year, int month, int day)
    {
        String jpql = "select d from Diary d " +
                "where d.author = :author " +
                "and d.createYear = :year " +
                "and d.createMonth = :month " +
                "and d.createDay = : day";

        return entityManager.createQuery(jpql, Diary.class)
                .setParameter("author", member)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("day", day)
                .getResultList();
    }

    //==일기삭제==//
    public Boolean deleteById(Long diaryId)
    {
        Diary diary = entityManager.find(Diary.class, diaryId);
        entityManager.remove(diary);
        return true;
    }
}
