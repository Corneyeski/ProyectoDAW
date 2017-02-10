package proyecto.repository;

import proyecto.domain.Connection;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Connection entity.
 */
@SuppressWarnings("unused")
public interface ConnectionRepository extends JpaRepository<Connection,Long> {

    @Query("select connection from Connection connection where connection.user.login = ?#{principal.username}")
    List<Connection> findByUserIsCurrentUser();

}
