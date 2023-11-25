package msds.homefarming.domain.dto.chatbotDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SingleDialogDto
{
    private Long id;
    private String speaker;
    private LocalDateTime speakTime;
    private String content;

    public static SingleDialogDto create(Long id, LocalDateTime speakTime, String speaker, String content)
    {
        SingleDialogDto dialog = new SingleDialogDto();
        dialog.id = id;
        dialog.speakTime = speakTime;
        dialog.speaker = speaker;
        dialog.content = content;
        return dialog;
    }
}
