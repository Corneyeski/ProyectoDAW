package proyecto.repository;

import org.springframework.data.repository.query.Param;
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

    @Query("select photo from Photo photo" +
        " where photo.points > 2.9 AND photo.user.userExt.city = :city " +
        "ORDER BY photo.time desc ")
    List<Photo> findUserExtPopularGreaterThan(@Param("city")String city);

    @Query("select photo from Photo photo" +
        " where :user member of photo.user.followedUsers " +
        "ORDER BY photo.time desc ")
    List<Photo> findUserExtFollowing(@Param("followedUsers")String followedUsers);
}

//and :user member of photo.user.bloquedUsers.blocked
