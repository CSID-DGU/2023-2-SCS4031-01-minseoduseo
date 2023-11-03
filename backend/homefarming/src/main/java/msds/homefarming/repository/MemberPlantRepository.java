package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import msds.homefarming.domain.MemberPlant;
import org.springframework.stereotype.Repository;

@Repository
public class MemberPlantRepository
{
    @PersistenceContext
    EntityManager entityManager;

    public MemberPlant save(MemberPlant plant)
    {
        entityManager.persist(plant);
        return plant;
    }

    public MemberPlant findByMemberPlantId(Long plantId)
    {
        return entityManager.find(MemberPlant.class, plantId);
    }
}
