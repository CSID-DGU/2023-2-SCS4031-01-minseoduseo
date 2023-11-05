package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.MemberPlant;
import msds.homefarming.domain.dto.memberPlantDto.DeletePlantResponseDto;
import msds.homefarming.domain.dto.memberPlantDto.GetPlantsResponseDto;
import msds.homefarming.domain.dto.memberPlantDto.RegisterPlantRequestDto;
import msds.homefarming.domain.dto.memberPlantDto.RegisterPlantResponseDto;
import msds.homefarming.exception.NoExistPlantException;
import msds.homefarming.repository.MemberPlantRepository;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberPlantService
{
    private final MemberPlantRepository memberPlantRepository;
    private final MemberRepository memberRepository;
    private final UserPrincipal userPrincipal;

    //==회원식물 등록==//
    @Transactional
    public RegisterPlantResponseDto register(Long memberId, RegisterPlantRequestDto requestDto)
    {
        MemberPlant createPlant = MemberPlant.create(
                requestDto.getImage(),
                requestDto.getName(),
                requestDto.getNickname(),
                requestDto.getCreateDate());

        MemberPlant plant = memberPlantRepository.save(createPlant);
        Member member = memberRepository.findById(memberId);
        plant.setOwner(member);

        return new RegisterPlantResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getName(),
                plant.getNickname(),
                plant.getCreateDate(),
                plant.getOwner().getNickname());
    }

    //==회원식물 리스트 조회==//
    public GetPlantsResponseDto findAll(Long memberId)
    {
        Member member = memberRepository.findById(memberId);
        List<MemberPlant> plants = memberPlantRepository.findByMemberId(memberId);

        GetPlantsResponseDto responseDto = new GetPlantsResponseDto();
        Integer count = 0;
        for(MemberPlant plant : plants)
        {
            RegisterPlantResponseDto myPlant =
                    new RegisterPlantResponseDto(
                    plant.getId(),
                    plant.getImage(),
                    plant.getName(),
                    plant.getNickname(),
                    plant.getCreateDate(),
                    plant.getOwner().getNickname());
            responseDto.getPlantList().add(myPlant);
            count++;
        }
        responseDto.setCount(count);
        responseDto.setUsername(member.getNickname());
        return responseDto;
    }

    //==회원식물 단건 조회==//
    public RegisterPlantResponseDto findOne(Long plantId)
    {
        MemberPlant plant = memberPlantRepository.findByMemberPlantId(plantId);
        if(plant == null)
        {
            throw new NoExistPlantException("존재하지 않는 회원식물 입니다.");
        }
        return new RegisterPlantResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getName(),
                plant.getNickname(),
                plant.getCreateDate(),
                plant.getOwner().getNickname()
        );
    }

    //==회원식물 업데이트==//
    @Transactional
    public RegisterPlantResponseDto update(Long plantId, MemberPlant updatePlant)
    {
        MemberPlant plant = memberPlantRepository.findByMemberPlantId(plantId);
        if(plant == null)
        {
            throw new NoExistPlantException("존재하지 않는 회원식물 입니다.");
        }
        plant.setImage(updatePlant.getImage());
        plant.setName(updatePlant.getName());
        plant.setNickname(updatePlant.getNickname());
        plant.setCreateDate(updatePlant.getCreateDate());

        return new RegisterPlantResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getName(),
                plant.getNickname(),
                plant.getCreateDate(),
                plant.getOwner().getNickname());
    }

    //==회원식물 삭제==//
    @Transactional
    public DeletePlantResponseDto deleteMemberPlant(Long plantId)
    {
        MemberPlant plant = memberPlantRepository.findByMemberPlantId(plantId);
        if(plant == null)
        {
            throw new NoExistPlantException("존재하지 않는 회원식물 입니다.");
        }

        Boolean result = memberPlantRepository.deleteByMemberPlantId(plantId);
        return new DeletePlantResponseDto(result, "삭제가 완료되었습니다.");
    }

}
