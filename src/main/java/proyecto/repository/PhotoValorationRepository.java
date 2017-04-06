package proyecto.repository;

import org.springframework.data.repository.query.Param;
import proyecto.domain.Photo;
import proyecto.domain.PhotoValoration;

import org.springframework.data.jpa.repository.*;
import proyecto.domain.User;

import java.util.List;

/**
 * Spring Data JPA repository for the PhotoValoration entity.
 */
@SuppressWarnings("unused")
public interface PhotoValorationRepository extends JpaRepository<PhotoValoration,Long> {

    @Query("select photoValoration from PhotoValoration photoValoration where photoValoration.user.login = ?#{principal.username}")
    List<PhotoValoration> findByUserIsCurrentUser();

    PhotoValoration findByUserAndPhoto(User user, Photo photo);

    @Query("select AVG(photoValoration.mark) FROM PhotoValoration photoValoration WHERE photoValoration.photo = :photo")
    Double avgPhoto(@Param("photo")Photo photo);
}
