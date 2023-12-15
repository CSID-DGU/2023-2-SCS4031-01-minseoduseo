package msds.homefarming.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ChatbotDialog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime speakTime;
    private String speaker;

    @Column(length = 10000)
    private String content;

    protected ChatbotDialog(LocalDateTime speakTime, String speaker, String content)
    {
        this.speakTime = speakTime;
        this.speaker = speaker;
        this.content = content;
    }

    protected ChatbotDialog()
    {
    }

    public static ChatbotDialog create(LocalDateTime speakTime, String speaker, String content)
    {
        return new ChatbotDialog(speakTime, speaker, content);
    }
}
