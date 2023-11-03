package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.Member;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService
{
    private final MemberRepository memberRepository;

    @Transactional
    public Member joinMember(Member member)
    {
        return memberRepository.save(member);
    }

    @Transactional
    public Member findMemberById(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

    @Transactional
    public Member findMemberByUsername(String username)
    {
        return memberRepository.findByUsername(username);
    }
}
