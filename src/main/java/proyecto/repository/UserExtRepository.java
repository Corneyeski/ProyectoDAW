package proyecto.repository;

import proyecto.domain.User;
import proyecto.domain.UserExt;

import org.springframework.data.jpa.repository.*;

import java.util.List;
@SuppressWarnings("unused")
public interface UserExtRepository extends JpaRepository<UserExt,Long> {

    UserExt findByUser(User user);
}
