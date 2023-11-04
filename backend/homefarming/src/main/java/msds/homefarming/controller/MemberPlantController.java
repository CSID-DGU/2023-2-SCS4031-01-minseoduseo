package msds.homefarming.controller;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.MemberPlant;
import msds.homefarming.domain.dto.MemberPlantRequestDto;
import msds.homefarming.service.MemberPlantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberPlantController
{
    private final MemberPlantService memberPlantService;
    private final UserPrincipal userPrincipal;

    // 회원식물 등록
    @PostMapping("/api/member/plant")
    public MemberPlant registerMemberPlantApi(
            @RequestBody MemberPlantRequestDto memberPlantRequestDto)
    {
        MemberPlant plant = new MemberPlant(memberPlantRequestDto.getImage(),
                memberPlantRequestDto.getName(),
                memberPlantRequestDto.getNickname(),
                memberPlantRequestDto.getCreateDate());
        return memberPlantService.registerMemberPlant(userPrincipal.getId(), plant);
    }

    // 회원식물 모두 조회
    @GetMapping("/api/member/plants")
    public List<MemberPlant> showMemberPlantListApi()
    {
        return memberPlantService.showMemberPlantList(userPrincipal.getId());
    }

    // 회원식물 하나 조회
    @GetMapping("/api/member/plant")
    public MemberPlant showMemberPlantApi(
            @RequestParam(name = "plantId") Long plantId)
    {
        return memberPlantService.showMemberPlant(plantId);
    }

    // 회원식물 갱신
    // PATCH: /api/member/plant?plantId=1
    // BODY: update plant 정보
    @PatchMapping("/api/member/plant")
    public MemberPlant updateMemberPlantApi(
            @RequestParam(name = "plantId") Long plantId,
            @RequestBody MemberPlantRequestDto memberPlantRequestDto)
    {
        MemberPlant plant = new MemberPlant(memberPlantRequestDto.getImage(),
                memberPlantRequestDto.getName(),
                memberPlantRequestDto.getNickname(),
                memberPlantRequestDto.getCreateDate());
        return memberPlantService.updateMemberPlant(plantId, plant);
    }

    //회원식물 삭제
    @DeleteMapping("/api/member/plant")
    public Boolean deleteMemberPlantApi(
            @RequestParam(name = "plantId") Long plantId)
    {
        return memberPlantService.deleteMemberPlant(plantId);
    }
}
