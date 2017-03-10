package proyecto.repository;

import org.springframework.stereotype.Repository;
import proyecto.domain.Offer;
import proyecto.domain.Offer_;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class OfferCriteriaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    OfferRepository offerRepository;

    private CriteriaBuilder builder;

    private CriteriaQuery<Offer> offerCriteriaQuery;

    private Root<Offer> offerRoot;

    @PostConstruct
    public void initCriteria(){
        builder = entityManager.getCriteriaBuilder();

        offerCriteriaQuery = builder.createQuery( Offer.class );

        offerRoot = offerCriteriaQuery.from( Offer.class );
    }

    public List<Offer> filterOfferDefinitions(Map<String, Object> parameters) {

        //TODO Añadir atributo Tags y localidad en Offer
        filterByName(parameters);

        return null;
    }

    private void filterByName(Map<String, Object> parameters) {
        if(parameters.containsKey("search")) {
            String searchName = (String) parameters.get("search");

            offerCriteriaQuery.select(offerRoot);
            offerCriteriaQuery.where(builder.like(offerRoot.get(Offer_.name), "%" + searchName + "%"));
        }
    }

    private void filterByTags(Map<String, Object> parameters) {
        if(parameters.containsKey("tags")) {
            String tags = (String) parameters.get("tags");

            offerCriteriaQuery.select(offerRoot);
            //offerCriteriaQuery.where(builder.like(offerRoot.get(Offer_.name), "%" + searchName + "%"));
        }
    }

    private void filterByCity(Map<String, Object> parameters) {
        if(parameters.containsKey("city")) {
            String city = (String) parameters.get("city");

            offerCriteriaQuery.select(offerRoot);
            //offerCriteriaQuery.where(builder.like(offerRoot.get(Offer_.city), "%" + city + "%"));
        }
    }

    private void filterByDate(Map<String, Object> parameters) {
        if(parameters.containsKey("date")) {
            ZonedDateTime date = (ZonedDateTime) parameters.get("date");

            offerCriteriaQuery.select(offerRoot);
            offerCriteriaQuery.where(builder.between(offerRoot.get(Offer_.time), date, ZonedDateTime.now()));
        }
    }
}
