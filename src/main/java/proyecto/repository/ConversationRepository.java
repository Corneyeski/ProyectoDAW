package proyecto.repository;

import proyecto.domain.Conversation;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Conversation entity.
 */
@SuppressWarnings("unused")
public interface ConversationRepository extends JpaRepository<Conversation,Long> {

    @Query("select conversation from Conversation conversation where conversation.user.login = ?#{principal.username}")
    List<Conversation> findByUserIsCurrentUser();



}
