package msds.homefarming.api;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.MemberPlant;
import msds.homefarming.domain.dto.memberPlantDto.*;
import msds.homefarming.service.MemberPlantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberPlantController
{
    private final MemberPlantService memberPlantService;
    private final UserPrincipal userPrincipal;

    //==회원식물 등록==//
    @PostMapping("/api/member/plant")
    public RegisterPlantResponseDto registerMemberPlant(
            @RequestBody RegisterPlantRequestDto requestDto)
    {
        return memberPlantService.register(userPrincipal.getId(), requestDto);
    }

    //==회원식물 모두 조회==//
    @GetMapping("/api/member/plants")
    public GetPlantsResponseDto getMemberPlants()
    {
        return memberPlantService.findAll(userPrincipal.getId());
    }

    //==회원식물 하나 조회==//
    @GetMapping("/api/member/plant")
    public RegisterPlantResponseDto showMemberPlantApi(
            @RequestParam(name = "plantId") Long plantId)
    {
        return memberPlantService.findOne(plantId);
    }

    //==회원식물 갱신==//
    @PutMapping("/api/member/plant")
    public RegisterPlantResponseDto updateMemberPlantApi(
            @RequestParam(name = "plantId") Long plantId,
            @RequestBody MemberPlantRequestDto requestDto)
    {
        MemberPlant plant = MemberPlant.create(
                requestDto.getImage(),
                requestDto.getColor(),
                requestDto.getName(),
                requestDto.getNickname(),
                requestDto.getCreateDate());
        return memberPlantService.update(plantId, plant);
    }

    //==회원식물 삭제==//
    @DeleteMapping("/api/member/plant")
    public DeletePlantResponseDto deleteMemberPlantApi(
            @RequestParam(name = "plantId") Long plantId)
    {
        return memberPlantService.deleteMemberPlant(plantId);
    }
}
