package proyecto.repository;

import proyecto.domain.Photo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Photo entity.
 */
@SuppressWarnings("unused")
public interface PhotoRepository extends JpaRepository<Photo,Long> {

    @Query("select photo from Photo photo where photo.user.login = ?#{principal.username}")
    List<Photo> findByUserIsCurrentUser();

    @Query("select photo from Photo photo where photo.user.id > 2.9")
    List<Photo> findUserPopularGreaterThan();
}
