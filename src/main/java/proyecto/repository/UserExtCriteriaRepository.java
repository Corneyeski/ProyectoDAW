package proyecto.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import proyecto.domain.User;
import proyecto.domain.UserExt;
import proyecto.domain.UserExt_;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
public class UserExtCriteriaRepository {

    @PersistenceContext
    EntityManager entityManager;

    private CriteriaBuilder builder;

    private CriteriaQuery<UserExt> userExtCriteriaQuery;

    private Root<UserExt> userExtRoot;

    @PostConstruct
    public void initCriteria(){
        builder = entityManager.getCriteriaBuilder();

        userExtCriteriaQuery = builder.createQuery( UserExt.class );

        userExtRoot = userExtCriteriaQuery.from( UserExt.class );
    }


    public List<UserExt> filterUserextDefinitions(Map<String, Object> parameters) {

        //Criteria userExtDefinitionCriteria = currentSession().createCriteria(UserExt.class);
       // userExtDefinitionCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        //Criteria mottoCriteria = userExtDefinitionCriteria.createCriteria("userext");


        filterByUserExt(parameters);

        filterByValidated(parameters);

        filterByMateria(parameters);

        filterByRegion(parameters);

        filterByRegistro(parameters);

        return entityManager.createQuery( userExtCriteriaQuery ).getResultList();
    }

    private void filterByUserExt(Map<String, Object> parameters) {
        if(parameters.containsKey("city")) {
            String searchName = (String) parameters.get("city");

            userExtCriteriaQuery.select(userExtRoot);
            userExtCriteriaQuery.where(builder.like(userExtRoot.get(UserExt_.city), "%" + searchName + "%"));
        }
    }

    private void filterByValidated(Map<String, Object> parameters) {
        if (parameters.containsKey("validated")) {
            boolean validated = (boolean) parameters.get("validated");

            userExtCriteriaQuery.select(userExtRoot);
            if (validated) {
                userExtCriteriaQuery.where(builder.isTrue(userExtRoot.get(UserExt_.validated)));
            }else{
                userExtCriteriaQuery.where(builder.isFalse(userExtRoot.get(UserExt_.validated)));
            }
        }
    }

    private void filterByMateria(Map<String, Object> parameters) {
        if (parameters.containsKey("minPopular") || parameters.containsKey("maxPopular")) {

            userExtCriteriaQuery.select(userExtRoot);

            if(parameters.containsKey("minPopular") && parameters.containsKey("maxPopular")){

                Double minPopular = (Double) parameters.get("minPopular");
                Double maxPopular = (Double) parameters.get("maxPopular");

                userExtCriteriaQuery.where(builder.between(userExtRoot.get(UserExt_.popular), minPopular, maxPopular));
            }
            if(parameters.containsKey("minPopular") && !parameters.containsKey("maxPopular")){

                Double minPopular = (Double) parameters.get("minPopular");

                userExtCriteriaQuery.where(builder.greaterThanOrEqualTo(userExtRoot.get(UserExt_.popular), minPopular));
            }
            if(parameters.containsKey("maxPopular") && !parameters.containsKey("minPopular")){

                Double maxPopular = (Double) parameters.get("maxPopular");

                userExtCriteriaQuery.where(builder.lessThanOrEqualTo(userExtRoot.get(UserExt_.popular), maxPopular));
            }
        }
    }

    private void filterByRegion(Map<String, Object> parameters) {
        if (parameters.containsKey("regiones")) {

        }
    }

    private void filterByRegistro(Map<String, Object> parameters) {
        if (parameters.containsKey("registros")) {

        }
    }
}
