package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.ChatbotDialog;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.dto.chatbotDto.AnswerDto;
import msds.homefarming.repository.ChatbotRepository;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatbotService
{
    private final ChatbotRepository chatbotRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long memberId, String speaker, String content)
    {
        ChatbotDialog dialog =
                ChatbotDialog.create(
                LocalDateTime.now(),
                speaker,
                content);

        chatbotRepository.save(dialog);
        Member member = memberRepository.findById(memberId);
        dialog.setMember(member);
    }
}
