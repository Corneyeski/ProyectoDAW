package proyecto.repository;

import proyecto.domain.Photo;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public class PhotoCriteriaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    UserExtRepository PhotoRepository;

    private CriteriaBuilder builder;

    private CriteriaQuery<Photo> PhotoCriteriaQuery;

    private Root<Photo> userExtRoot;

    @PostConstruct
    public void initCriteria(){
        builder = entityManager.getCriteriaBuilder();

        PhotoCriteriaQuery = builder.createQuery( Photo.class );

        userExtRoot = PhotoCriteriaQuery.from( Photo.class );
    }


    public List<Photo> filterPhotoDefinitions(Map<String, Object> parameters) {


        return null;
    }
}
