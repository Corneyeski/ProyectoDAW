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
import javax.inject.Inject;
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

    @Inject
    UserExtRepository userExtRepository;

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

        filterByUserExt(parameters);

        filterByValidated(parameters);

        filterByPopular(parameters);

        filterByAge(parameters);

        filterByTags(parameters);

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

    private void filterByPopular(Map<String, Object> parameters) {
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

    private void filterByAge(Map<String, Object> parameters) {
        if (parameters.containsKey("minAge") || parameters.containsKey("maxAge")) {

            userExtCriteriaQuery.select(userExtRoot);

            if(parameters.containsKey("minAge") && parameters.containsKey("maxAge")){

                Integer minPopular = (Integer) parameters.get("minAge");
                Integer maxPopular = (Integer) parameters.get("maxAge");

                //TODO añadir atributo de edad

                //userExtCriteriaQuery.where(builder.between(userExtRoot.get(UserExt_.age), minPopular, maxPopular));
            }
            if(parameters.containsKey("minAge") && !parameters.containsKey("maxAge")){

                Double minPopular = (Double) parameters.get("minAge");

                //userExtCriteriaQuery.where(builder.between(userExtRoot.get(UserExt_.age), minPopular, maxPopular));
            }
            if(parameters.containsKey("maxAge") && !parameters.containsKey("minAge")){

                Double maxPopular = (Double) parameters.get("maxAge");

                //userExtCriteriaQuery.where(builder.between(userExtRoot.get(UserExt_.age), minPopular, maxPopular));
            }
        }
    }

    private void filterByTags(Map<String, Object> parameters) {
        if (parameters.containsKey("tags")) {

            userExtCriteriaQuery.select(userExtRoot);

            String tags = (String) parameters.get("tags");

            String[] tag = tags.split("#");

            System.out.println(tag);

            for(int i = 1; i < tag.length-1; i++){
                userExtCriteriaQuery.where(builder.like(userExtRoot.get(UserExt_.tags), "%" + tag[i] + "%"));
            }
        }
    }
}
