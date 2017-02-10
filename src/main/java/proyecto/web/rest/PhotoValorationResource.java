package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import proyecto.domain.PhotoValoration;

import proyecto.repository.PhotoValorationRepository;
import proyecto.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PhotoValoration.
 */
@RestController
@RequestMapping("/api")
public class PhotoValorationResource {

    private final Logger log = LoggerFactory.getLogger(PhotoValorationResource.class);

    private static final String ENTITY_NAME = "photoValoration";
        
    private final PhotoValorationRepository photoValorationRepository;

    public PhotoValorationResource(PhotoValorationRepository photoValorationRepository) {
        this.photoValorationRepository = photoValorationRepository;
    }

    /**
     * POST  /photo-valorations : Create a new photoValoration.
     *
     * @param photoValoration the photoValoration to create
     * @return the ResponseEntity with status 201 (Created) and with body the new photoValoration, or with status 400 (Bad Request) if the photoValoration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/photo-valorations")
    @Timed
    public ResponseEntity<PhotoValoration> createPhotoValoration(@RequestBody PhotoValoration photoValoration) throws URISyntaxException {
        log.debug("REST request to save PhotoValoration : {}", photoValoration);
        if (photoValoration.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new photoValoration cannot already have an ID")).body(null);
        }
        PhotoValoration result = photoValorationRepository.save(photoValoration);
        return ResponseEntity.created(new URI("/api/photo-valorations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /photo-valorations : Updates an existing photoValoration.
     *
     * @param photoValoration the photoValoration to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated photoValoration,
     * or with status 400 (Bad Request) if the photoValoration is not valid,
     * or with status 500 (Internal Server Error) if the photoValoration couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/photo-valorations")
    @Timed
    public ResponseEntity<PhotoValoration> updatePhotoValoration(@RequestBody PhotoValoration photoValoration) throws URISyntaxException {
        log.debug("REST request to update PhotoValoration : {}", photoValoration);
        if (photoValoration.getId() == null) {
            return createPhotoValoration(photoValoration);
        }
        PhotoValoration result = photoValorationRepository.save(photoValoration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, photoValoration.getId().toString()))
            .body(result);
    }

    /**
     * GET  /photo-valorations : get all the photoValorations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of photoValorations in body
     */
    @GetMapping("/photo-valorations")
    @Timed
    public List<PhotoValoration> getAllPhotoValorations() {
        log.debug("REST request to get all PhotoValorations");
        List<PhotoValoration> photoValorations = photoValorationRepository.findAll();
        return photoValorations;
    }

    /**
     * GET  /photo-valorations/:id : get the "id" photoValoration.
     *
     * @param id the id of the photoValoration to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the photoValoration, or with status 404 (Not Found)
     */
    @GetMapping("/photo-valorations/{id}")
    @Timed
    public ResponseEntity<PhotoValoration> getPhotoValoration(@PathVariable Long id) {
        log.debug("REST request to get PhotoValoration : {}", id);
        PhotoValoration photoValoration = photoValorationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(photoValoration));
    }

    /**
     * DELETE  /photo-valorations/:id : delete the "id" photoValoration.
     *
     * @param id the id of the photoValoration to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/photo-valorations/{id}")
    @Timed
    public ResponseEntity<Void> deletePhotoValoration(@PathVariable Long id) {
        log.debug("REST request to delete PhotoValoration : {}", id);
        photoValorationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
