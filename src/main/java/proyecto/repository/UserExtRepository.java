package proyecto.repository;

import proyecto.domain.UserExt;

import org.springframework.data.jpa.repository.*;

@SuppressWarnings("unused")
public interface UserExtRepository extends JpaRepository<UserExt,Long> {

}
