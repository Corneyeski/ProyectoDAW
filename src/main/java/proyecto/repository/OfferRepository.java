package proyecto.repository;

import proyecto.domain.Offer;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Offer entity.
 */
@SuppressWarnings("unused")
public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Query("select distinct offer from Offer offer left join fetch offer.users")
    List<Offer> findAllWithEagerRelationships();

    @Query("select offer from Offer offer left join fetch offer.users where offer.id =:id")
    Offer findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select offer from Offer offer where offer.closed = false order by offer.time desc")
    List<Offer> findOfferOrderByDateAndNotClosed();

}
