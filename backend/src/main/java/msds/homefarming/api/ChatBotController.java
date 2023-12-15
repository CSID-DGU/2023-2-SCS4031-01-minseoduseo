package msds.homefarming.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.dto.chatbotDto.AnswerDto;
import msds.homefarming.domain.dto.chatbotDto.DialogListDto;
import msds.homefarming.domain.dto.chatbotDto.QuestionDto;
import msds.homefarming.exception.ChatbotTimeoutException;
import msds.homefarming.service.ChatbotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
public class ChatBotController
{
    private final ChatbotService chatbotService;
    private final UserPrincipal userPrincipal;

    @Value("${msds.chatbot.open-ai-uri}")
    private String AI_CHATBOT_URI;

    @PostMapping("/api/member/chatbot")
    public AnswerDto getChatBotAnswer(@RequestBody QuestionDto request)
    {
        System.out.println("//==ChatBot Controller 진입!!==//");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"question\" : \"" + request.getQuestion() + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try
        {
            ResponseEntity<AnswerDto> responseEntity = restTemplate.postForEntity(AI_CHATBOT_URI, requestEntity, AnswerDto.class);
            //==챗봇 질문 저장==//
            chatbotService.save(userPrincipal.getId(), "member",request.getQuestion());
            //==챗봇 질문 저장끝==///

            //==챗봇 응답 저장==//
            chatbotService.save(userPrincipal.getId(), "ai", responseEntity.getBody().getAnswer());
            //==챗봇 응답 저장끝==//
            return responseEntity.getBody();
        }
        catch (Exception e)
        {
            throw new ChatbotTimeoutException("채팅 응답시간이 1분을 초과했습니다. 다시 질문 해주세요.");
        }
    }

    @GetMapping("/api/member/chatbot/history")
    public DialogListDto getChatbotHistory()
    {
        return chatbotService.getHistory(userPrincipal.getId());
    }
}

