package msds.homefarming.service;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.ChatbotDialog;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.dto.chatbotDto.AnswerDto;
import msds.homefarming.domain.dto.chatbotDto.DialogListDto;
import msds.homefarming.domain.dto.chatbotDto.SingleDialogDto;
import msds.homefarming.repository.ChatbotRepository;
import msds.homefarming.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatbotService
{
    private final ChatbotRepository chatbotRepository;
    private final MemberRepository memberRepository;

    //==챗봇 대화내용 저장==//
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

    //==챗봇 대화내용 조회==//
    public DialogListDto getHistory(Long memberId)
    {
        Member member = memberRepository.findById(memberId);
        List<ChatbotDialog> history = chatbotRepository.findByMember(member);

        DialogListDto dialogListDto = new DialogListDto();
        dialogListDto.setHistory(new ArrayList<>());
        dialogListDto.setMemberId(memberId);
        dialogListDto.setMemberName(member.getNickname());
        Long count = 0L;
        for (ChatbotDialog dialog : history)
        {
            count++;
            SingleDialogDto dialogEntity = SingleDialogDto.create(
                    dialog.getId(),
                    dialog.getSpeakTime(),
                    dialog.getSpeaker(),
                    dialog.getContent());
            dialogListDto.getHistory().add(dialogEntity);
        }
        dialogListDto.setCount(count);

        return dialogListDto;
    }
}
