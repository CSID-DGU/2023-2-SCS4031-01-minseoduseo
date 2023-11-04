package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import msds.homefarming.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemberRepository
{
    @PersistenceContext
    EntityManager entityManager;

    public Member save(Member member)
    {
        entityManager.persist(member);
        return member;
    }

    public Member findById(Long memberId)
    {
        return entityManager.find(Member.class, memberId);
    }

    public Member findByUsername(String username)
    {
        String jpql = "SELECT m FROM Member m WHERE m.username = :username";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter("username", username);
        List<Member> members = query.getResultList();
        if(members.size() <= 0)
        {
            return null;
        }
        else
        {
            return members.get(0);
        }
    }
}