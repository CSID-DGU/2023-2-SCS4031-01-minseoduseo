package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.Member;
import msds.homefarming.exception.NoExistMemberException;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository
{
    private final EntityManager entityManager;

    //==회원저장==//
    public Member save(Member member)
    {
        entityManager.persist(member);
        return member;
    }

    //==회원 아이디 조회==//
    public Member findById(Long memberId)
    {
        Member member = entityManager.find(Member.class, memberId);
        if (member == null)
        {
            throw new NoExistMemberException("존재하지 않는 회원입니다.");
        }
        return member;
    }

    //==회원 유저네임 조회==//
    public Member findByUsername(String username)
    {
        List<Member> members = entityManager.createQuery(
                        "select m from Member m where m.username = :username",Member.class)
                .setParameter("username", username)
                .getResultList();
        if(members.size() <= 0)
        {
            return null;
        }
        else {
            return members.get(0);
        }
    }

    //==회원삭제==//
    public Boolean deleteById(Long memberId)
    {
        Member principal = findById(memberId);
        if (principal == null)
        {
            throw new NoExistMemberException("존재하지 않는 회원입니다.");
        }
        entityManager.remove(principal);
        return true;

    }
}