package msds.homefarming.repository;

import msds.homefarming.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long>
{
    Member findMemberByUsername(String username);
}
