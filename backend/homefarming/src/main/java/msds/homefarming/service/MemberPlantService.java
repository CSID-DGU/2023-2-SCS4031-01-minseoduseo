package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.MemberPlant;
import msds.homefarming.repository.MemberPlantRepository;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberPlantService
{
    private final MemberPlantRepository memberPlantRepository;
    private final MemberRepository memberRepository;
    private final UserPrincipal userPrincipal;

    @Transactional
    public MemberPlant registerMemberPlant(MemberPlant plant)
    {
        MemberPlant savePlant = memberPlantRepository.save(plant);
        Member findMember = memberRepository.findById(userPrincipal.getId());
        savePlant.setOwner(findMember);
        return savePlant;
    }
}
