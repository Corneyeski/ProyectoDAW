package proyecto.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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

    public List<UserExt> filterUserextDefinitions(Map<String, Object> parameters) {

        Criteria userExtDefinitionCriteria = currentSession().createCriteria(UserExt.class);
        userExtDefinitionCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        //Criteria mottoCriteria = userExtDefinitionCriteria.createCriteria("userext");

        filterByUserExt(parameters, userExtDefinitionCriteria);

        /*filterByCategoria(parameters, userExtDefinitionCriteria);

        filterByMateria(parameters, userExtDefinitionCriteria);

        filterByRegion(parameters, userExtDefinitionCriteria);

        filterByRegistro(parameters, userExtDefinitionCriteria);*/

        List<UserExt> results = userExtDefinitionCriteria.list();

        return results;
    }

    private void filterByUserExt(Map<String, Object> parameters, Criteria userExtCriteria) {


        String searchName = (String) parameters.get("city");
        userExtCriteria.add(Restrictions.ilike("city", searchName, MatchMode.ANYWHERE));

        //TODO: Aquí es dónde se filtra el tipo de búsqueda ( Empieza por, acaba en, etc...) habría que añadir u case más para el default o poner en el front a default el 1 y que quite el restriction

    }

    private void filterByCategoria(Map<String, Object> parameters, Criteria mottoDefinitionCriteria) {
        if (parameters.containsKey("categorias")) {
            String[] categorias = (String[]) parameters.get("categorias");
            mottoDefinitionCriteria.add(Restrictions.in("categoria", categorias));
        }
    }

    private void filterByMateria(Map<String, Object> parameters, Criteria mottoDefinitionCriteria) {
        if (parameters.containsKey("materias")) {
            String[] materias = (String[]) parameters.get("materias");
            mottoDefinitionCriteria.add(Restrictions.in("materia", materias));
        }
    }

    private void filterByRegion(Map<String, Object> parameters, Criteria mottoDefinitionCriteria) {
        if (parameters.containsKey("regiones")) {
            String[] regiones = (String[]) parameters.get("regiones");
            mottoDefinitionCriteria.add(Restrictions.in("region", regiones));
        }
    }

    private void filterByRegistro(Map<String, Object> parameters, Criteria mottoDefinitionCriteria) {
        if (parameters.containsKey("registros")) {
            String[] registros = (String[]) parameters.get("registros");
            mottoDefinitionCriteria.add(Restrictions.in("registro", registros));
        }
    }
}
