package proyecto.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import proyecto.domain.UserExt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class UserExtCriteriaRepository {

    @PersistenceContext
    EntityManager entityManager;

    protected Session currentSession() {
        return entityManager.unwrap(Session.class);
    }

    /*public List<UserExt> filterUserextDefinitions(Map<String, Object> parameters) {

        Criteria userExtDefinitionCriteria = currentSession().createCriteria(UserExt.class);
        userExtDefinitionCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        Criteria mottoCriteria = userExtDefinitionCriteria.createCriteria("userext");

        filterBySearchBy(parameters, mottoCriteria);

        filterByCategoria(parameters, userExtDefinitionCriteria);

        filterByMateria(parameters, userExtDefinitionCriteria);

        filterByRegion(parameters, userExtDefinitionCriteria);

        filterByRegistro(parameters, userExtDefinitionCriteria);

        List<UserExt> results = mottoCriteria.list();

        return results;
    }*/
}
