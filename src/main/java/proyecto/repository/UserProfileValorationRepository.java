package proyecto.repository;

import org.springframework.data.repository.query.Param;
import proyecto.domain.User;
import proyecto.domain.UserProfileValoration;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserProfileValoration entity.
 */
@SuppressWarnings("unused")
public interface UserProfileValorationRepository extends JpaRepository<UserProfileValoration,Long> {

    @Query("select userProfileValoration from UserProfileValoration userProfileValoration where userProfileValoration.valorador.login = ?#{principal.username}")
    List<UserProfileValoration> findByValoradorIsCurrentUser();

    @Query("select userProfileValoration from UserProfileValoration userProfileValoration where userProfileValoration.valorado.login = ?#{principal.username}")
    List<UserProfileValoration> findByValoradoIsCurrentUser();

    UserProfileValoration findByValoradorAndValorado(User user, User userTwo);

    @Query("select AVG(userProfileValoration.value) FROM  UserProfileValoration userProfileValoration WHERE userProfileValoration.valorado = :user")
    Double avgUserPoints(@Param("user")User user);
}
