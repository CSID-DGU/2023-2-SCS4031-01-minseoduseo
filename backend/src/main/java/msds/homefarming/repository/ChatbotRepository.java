package msds.homefarming.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.ChatbotDialog;
import msds.homefarming.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatbotRepository
{
    private final EntityManager entityManager;

    //==챗봇 내용 DB에 저장==//
    public ChatbotDialog save(ChatbotDialog chatbotDialog)
    {
        entityManager.persist(chatbotDialog);
        return chatbotDialog;
    }

    //==회원의 챗봇 내용 리스트 조회==//
    public List<ChatbotDialog> findByMember(Member member)
    {
        String jpql = "select cd from ChatbotDialog cd " +
                "where cd.member = :member";

        return entityManager.createQuery(jpql, ChatbotDialog.class)
                .setParameter("member",member)
                .getResultList();
    }
}
