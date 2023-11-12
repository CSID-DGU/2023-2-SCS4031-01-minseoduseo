package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.DictionaryPlant;
import msds.homefarming.exception.AlreadyExistDictionaryException;
import msds.homefarming.exception.NoExistDictionaryException;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class DictionaryPlantRepository
{
    private final EntityManager entityManager;

    //==도감식물 등록==//
    public DictionaryPlant save(DictionaryPlant dictionaryPlant)
    {
        entityManager.persist(dictionaryPlant);
        return dictionaryPlant;
    }

    //==도감식물 모두 조회==//
    public List<DictionaryPlant> findAll()
    {
        return entityManager.createQuery("select p from DictionaryPlant p", DictionaryPlant.class)
                .getResultList();
    }

    //==도감식물 조회(아이디)==//
    public DictionaryPlant findById(Long dictionaryId)
    {
        return entityManager.find(DictionaryPlant.class, dictionaryId);
    }

    //==도감식물 조회(식물명)==//
    public DictionaryPlant findByName(String plantName)
    {
        try
        {
            return entityManager.createQuery("select p from DictionaryPlant p " +
                            "where p.name = :plantName", DictionaryPlant.class)
                    .setParameter("plantName", plantName)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    //==도감식물 삭제==//
    public Boolean deleteById(Long dictionaryPlantId)
    {
        DictionaryPlant dictionaryPlant = entityManager.find(DictionaryPlant.class, dictionaryPlantId);
        entityManager.remove(dictionaryPlant);
        return true;
    }
}
