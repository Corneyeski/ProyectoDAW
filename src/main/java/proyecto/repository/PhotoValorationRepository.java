package proyecto.repository;

import proyecto.domain.PhotoValoration;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PhotoValoration entity.
 */
@SuppressWarnings("unused")
public interface PhotoValorationRepository extends JpaRepository<PhotoValoration,Long> {

    @Query("select photoValoration from PhotoValoration photoValoration where photoValoration.user.login = ?#{principal.username}")
    List<PhotoValoration> findByUserIsCurrentUser();

}
