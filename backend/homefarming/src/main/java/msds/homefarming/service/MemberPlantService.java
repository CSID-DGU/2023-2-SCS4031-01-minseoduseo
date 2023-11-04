package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.MemberPlant;
import msds.homefarming.repository.MemberPlantRepository;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberPlantService
{
    private final MemberPlantRepository memberPlantRepository;
    private final MemberRepository memberRepository;

    // 회원 식물 등록하기
    @Transactional
    public MemberPlant registerMemberPlant(Long memberId, MemberPlant plant)
    {
        MemberPlant savePlant = memberPlantRepository.save(plant);
        Member findMember = memberRepository.findById(memberId);
        savePlant.setOwner(findMember);
        return savePlant;
    }

    // 회원 모든 식물 보여주기
    @Transactional(readOnly = true)
    public List<MemberPlant> showMemberPlantList(Long memberId)
    {
        return memberPlantRepository.findByMemberId(memberId);
    }

    // 회원 식물 하나 보여주기
    @Transactional(readOnly = true)
    public MemberPlant showMemberPlant(Long plantId)
    {
        return memberPlantRepository.findByMemberPlantId(plantId);
    }

    // 회원 식물 업데이트
    @Transactional
    public MemberPlant updateMemberPlant(Long plantId, MemberPlant updatePlant)
    {
        MemberPlant findPlant = memberPlantRepository.findByMemberPlantId(plantId);
        findPlant.setImage(updatePlant.getImage());
        findPlant.setName(updatePlant.getName());
        findPlant.setNickname(updatePlant.getNickname());
        findPlant.setCreateDate(updatePlant.getCreateDate());
        return findPlant;
    }

    // 회원 식물 삭제하기
    @Transactional
    public Boolean deleteMemberPlant(Long plantId)
    {
        return memberPlantRepository.deleteByMemberPlantId(plantId);
    }
}
