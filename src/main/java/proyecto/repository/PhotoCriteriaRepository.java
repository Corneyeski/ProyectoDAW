package proyecto.repository;

import org.springframework.stereotype.Repository;
import proyecto.domain.Photo;
import proyecto.domain.Photo_;

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
public class PhotoCriteriaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PhotoRepository PhotoRepository;

    private CriteriaBuilder builder;

    private CriteriaQuery<Photo> photoCriteriaQuery;

    private Root<Photo> userExtRoot;

    @PostConstruct
    public void initCriteria(){
        builder = entityManager.getCriteriaBuilder();

        photoCriteriaQuery = builder.createQuery( Photo.class );

        userExtRoot = photoCriteriaQuery.from( Photo.class );
    }


    public List<Photo> filterPhotoDefinitions(Map<String, Object> parameters) {

        filterByUserName(parameters);

        filterByTags(parameters);

        return entityManager.createQuery( photoCriteriaQuery ).getResultList();
    }

    private void filterByUserName(Map<String, Object> parameters) {

        if(parameters.containsKey("username")){
            String searchName = (String) parameters.get("username");

            photoCriteriaQuery.select(userExtRoot);
            //photoCriteriaQuery.where(builder.like(userExtRoot.get(Photo_.user.), "%" + searchName + "%"));
        }
    }
    private void filterByTags(Map<String, Object> parameters) {
        if (parameters.containsKey("tags")) {

            photoCriteriaQuery.select(userExtRoot);

            String tags = (String) parameters.get("tags");

            String[] tag = tags.split("#");

            System.out.println(tag);

            for(int i = 1; i < tag.length-1; i++){
                photoCriteriaQuery.where(builder.like(userExtRoot.get(Photo_.tags), "%" + tag[i] + "%"));
            }
        }

    }
}
