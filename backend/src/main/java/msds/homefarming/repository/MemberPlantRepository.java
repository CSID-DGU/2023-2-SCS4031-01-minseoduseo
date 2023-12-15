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

    //==회원식물 저장==//
    public MemberPlant save(MemberPlant plant)
    {
        entityManager.persist(plant);
        return plant;
    }

    //==회원식물 단건 조회==//
    public MemberPlant findByMemberPlantId(Long plantId)
    {
        return entityManager.find(MemberPlant.class, plantId);
    }

    //==회원식물 모두 조회==//
    public List<MemberPlant> findByMemberId(Long memberId)
    {
        return entityManager.createQuery("select mp from MemberPlant mp where mp.owner.id = :memberId", MemberPlant.class)
                .setParameter("memberId",memberId)
                .getResultList();
    }

    //==특정 회원식물의 색깔 조회==//
    public String findColor(Long memberId, String plantName)
    {
        String color = "000000";
        List<MemberPlant> plants = findByMemberId(memberId);
        for(MemberPlant p : plants)
        {
            if(p.getName().equals(plantName))
            {
                color = p.getColor();
                break;
            }
        }
        return color;
    }

    //==회원 식물 삭제==//
    public Boolean deleteByMemberPlantId(Long plantId)
    {
        MemberPlant plantEntity = entityManager.find(MemberPlant.class, plantId);
        entityManager.remove(plantEntity);
        return true;
    }
}
