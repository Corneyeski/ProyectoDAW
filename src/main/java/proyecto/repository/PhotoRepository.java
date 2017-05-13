package proyecto.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import proyecto.domain.Photo;

import org.springframework.data.jpa.repository.*;
import proyecto.domain.User;

import java.util.Collection;
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
    Page<Photo> findUserExtPopularGreaterThan(@Param("city")String city, Pageable pageable);

    @Query("select photo from Photo photo" +
        " where photo.points > 2.9 AND photo.user.userExt.city = :city AND photo.user not in :users" +
        " ORDER BY photo.time desc ")
    Page<Photo> findUserExtPopularGreaterThanBlocked(@Param("city")String city,@Param("users")Collection<User> users,Pageable pageable);

    @Query("select photo from Photo photo" +
        " where photo.user in :followedUsers " +
        "ORDER BY photo.time desc")
    List<Photo> findUserExtFollowing(@Param("followedUsers")Collection<User> followedUsers);

    List<Photo> findByUser(User user);
}

//and :user member of photo.user.bloquedUsers.blocked
