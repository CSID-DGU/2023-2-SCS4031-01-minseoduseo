package msds.homefarming.api;

import lombok.Data;
import msds.homefarming.exception.ChatbotTimeoutException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatBotController
{
    @Value("${msds.chatbot.open-ai-uri}")
    private String AI_CHATBOT_URI;

    @PostMapping("/api/chatbot")
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
            return responseEntity.getBody();
        }
        catch (Exception e)
        {
            throw new ChatbotTimeoutException("채팅 응답시간이 1분을 초과했습니다. 다시 질문 해주세요.");
        }
    }

}

@Data
class QuestionDto
{
    private String question;
}

@Data
class AnswerDto
{
    private String answer;
}
