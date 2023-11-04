package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import msds.homefarming.domain.MemberPlant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberPlantRepository
{
    @PersistenceContext
    EntityManager entityManager;

    // 회원 식물 저장
    public MemberPlant save(MemberPlant plant)
    {
        entityManager.persist(plant);
        return plant;
    }

    // 회원 식물 찾기 (아이디로 찾기)
    public MemberPlant findByMemberPlantId(Long plantId)
    {
        return entityManager.find(MemberPlant.class, plantId);
    }

    // 회원 식물 모두 찾기 (주인 아이디로 찾기)
    public List<MemberPlant> findByMemberId(Long memberId)
    {
        String jpql = "select mp from MemberPlant mp where mp.owner.id = :memberId";
        return entityManager.createQuery(jpql, MemberPlant.class)
                .setParameter("memberId",memberId)
                .getResultList();
    }

    // 회원 식물 삭제하기 
    public boolean deleteByMemberPlantId(Long plantId)
    {
        MemberPlant plantEntity = entityManager.find(MemberPlant.class, plantId);
        entityManager.remove(plantEntity);
        return true;
    }
}
