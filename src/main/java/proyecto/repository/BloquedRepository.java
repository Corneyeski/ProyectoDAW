package proyecto.repository;

import proyecto.domain.Bloqued;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Bloqued entity.
 */
@SuppressWarnings("unused")
public interface BloquedRepository extends JpaRepository<Bloqued,Long> {

    @Query("select bloqued from Bloqued bloqued where bloqued.block.login = ?#{principal.username}")
    List<Bloqued> findByBlockIsCurrentUser();

    @Query("select bloqued from Bloqued bloqued where bloqued.blocked.login = ?#{principal.username}")
    List<Bloqued> findByBlockedIsCurrentUser();

}
