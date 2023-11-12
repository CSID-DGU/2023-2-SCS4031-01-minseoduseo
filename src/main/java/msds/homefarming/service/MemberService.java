package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.dto.memberDto.UpdatePrincipalRequestDto;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService
{
    private final MemberRepository memberRepository;

    //==회원가입==//
    @Transactional
    public Member join(Member member)
    {
        return memberRepository.save(member);
    }

    //회원정보 아이디 조회==//
    public Member findById(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

    //회원정보 유저네임 조회==//
    public Member findByUsername(String username)
    {
        return memberRepository.findByUsername(username);
    }

    //==회원정보 수정==//
    @Transactional
    public Member update(Long memberId, UpdatePrincipalRequestDto requestDto)
    {
        return memberRepository.findById(memberId)
                .update(requestDto.getImage(),
                        requestDto.getNickname());
    }

    //==회원정보 삭제==//
    @Transactional
    public Boolean deleteById(Long memberId)
    {
        return memberRepository.deleteById(memberId);
    }
}
