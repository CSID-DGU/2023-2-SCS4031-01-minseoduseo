package msds.homefarming.api;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.dto.memberDto.*;
import msds.homefarming.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController
{
    private final MemberService memberService;
    private final UserPrincipal userPrincipal;

    //==회원정보 조회==//
    @GetMapping("/api/member")
    public GetPrincipalResponseDto getPrincipal()
    {
        System.out.println("//==[/api/member] Cotroller진입==//");
        return new GetPrincipalResponseDto(
                userPrincipal.getId(),
                userPrincipal.getImage(),
                userPrincipal.getUsername(),
                userPrincipal.getNickname()
        );
    }

    //==회원정보 수정==//
    @PutMapping("/api/member")
    public UpdatePrincipalResponseDto updatePrincipal(
            @RequestBody UpdatePrincipalRequestDto requestDto)
    {
        Member principal = memberService.update(userPrincipal.getId(),requestDto);
        return new UpdatePrincipalResponseDto(
                principal.getId(),
                principal.getImage(),
                principal.getUsername(),
                principal.getNickname()
        );
    }

    //==회원정보 삭제==//
    @DeleteMapping("/api/member")
    public DeletePrincipalResponseDto deletePrincipal()
    {
        System.out.println("userPrincipal.getNickname() = " + userPrincipal.getNickname());
        Boolean result = memberService.deleteById(userPrincipal.getId(),userPrincipal.getUsername());
        return new DeletePrincipalResponseDto(result, "회원 삭제 완료");
    }
}
