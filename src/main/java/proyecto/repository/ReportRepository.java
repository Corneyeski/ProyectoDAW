package proyecto.repository;

import proyecto.domain.Report;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Report entity.
 */
@SuppressWarnings("unused")
public interface ReportRepository extends JpaRepository<Report,Long> {

    @Query("select report from Report report where report.user.login = ?#{principal.username}")
    List<Report> findByUserIsCurrentUser();

    @Query("select report from Report report where report.photo.login = ?#{principal.username}")
    List<Report> findByPhotoIsCurrentUser();

    @Query("select report from Report report where report.offer.login = ?#{principal.username}")
    List<Report> findByOfferIsCurrentUser();

}
