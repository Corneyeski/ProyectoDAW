package proyecto.repository;

import org.springframework.data.repository.query.Param;
import proyecto.domain.Bloqued;

import org.springframework.data.jpa.repository.*;
import proyecto.domain.User;

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

    @Query("select bloqued.blocked from Bloqued bloqued where bloqued.block = :user")
    List<User> selectBlockedFindByBlock(@Param("user")User user);

    List<Bloqued> findByBlock(User user);

    /*@Query("select  bloqued from Bloqued bloqued where bloqued.block =:block and bloqued.bloqued =:bloqued")
    Bloqued findExistByBlockAndBloquedAndBlocked(@Param("block")User block,@Param("bloqued")User bloqued);*/

    Bloqued findByBlockAndBlocked(User block, User blocked);

}
