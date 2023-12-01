package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.MemberPlant;
import msds.homefarming.domain.dto.memberPlantDto.DeletePlantResponseDto;
import msds.homefarming.domain.dto.memberPlantDto.GetPlantsResponseDto;
import msds.homefarming.domain.dto.memberPlantDto.RegisterPlantRequestDto;
import msds.homefarming.domain.dto.memberPlantDto.RegisterPlantResponseDto;
import msds.homefarming.exception.NoAuthorityException;
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
        //==색깔 추가==//
        MemberPlant createPlant = MemberPlant.create(
                requestDto.getImage(),
                requestDto.getColor(),
                requestDto.getName(),
                requestDto.getNickname(),
                requestDto.getCreateDate());

        MemberPlant plant = memberPlantRepository.save(createPlant);
        Member member = memberRepository.findById(memberId);
        plant.setOwner(member);

        //==식물색 추가==//
        return new RegisterPlantResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getColor(),
                plant.getName(),
                plant.getNickname(),
                plant.getCreateDate(),
                plant.getOwner().getNickname());
        //==식물색 추가==//
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
            //==식물색 추가==//
            RegisterPlantResponseDto myPlant =
                    new RegisterPlantResponseDto(
                            plant.getId(),
                            plant.getImage(),
                            plant.getColor(),
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

        if(!plant.getOwner().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 식물 조회 권한이 없는 회원입니다.");
        }

        //==식물색 추가==//
        return new RegisterPlantResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getColor(),
                plant.getName(),
                plant.getNickname(),
                plant.getCreateDate(),
                plant.getOwner().getNickname()
        );
        //==식물색 추가==//
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

        if(!plant.getOwner().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 식물 수정 권한이 없는 회원입니다.");
        }

        plant.setImage(updatePlant.getImage());
        //==식물색 추가==//
        plant.setColor(updatePlant.getColor());
        plant.setName(updatePlant.getName());
        plant.setNickname(updatePlant.getNickname());
        plant.setCreateDate(updatePlant.getCreateDate());

        //==식물색 추가==//
        return new RegisterPlantResponseDto(
                plant.getId(),
                plant.getImage(),
                plant.getColor(),
                plant.getName(),
                plant.getNickname(),
                plant.getCreateDate(),
                plant.getOwner().getNickname());
        //==식물색 추가==//
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

        if(!plant.getOwner().getUsername().equals(userPrincipal.getUsername()))
        {
            throw new NoAuthorityException("해당 식물 삭제 권한이 없는 회원입니다.");
        }

        Boolean result = memberPlantRepository.deleteByMemberPlantId(plantId);
        return new DeletePlantResponseDto(result, "삭제가 완료되었습니다.");
    }

}
