package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import proyecto.domain.Bloqued;

import proyecto.repository.BloquedRepository;
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
 * REST controller for managing Bloqued.
 */
@RestController
@RequestMapping("/api")
public class BloquedResource {

    private final Logger log = LoggerFactory.getLogger(BloquedResource.class);

    private static final String ENTITY_NAME = "bloqued";
        
    private final BloquedRepository bloquedRepository;

    public BloquedResource(BloquedRepository bloquedRepository) {
        this.bloquedRepository = bloquedRepository;
    }

    /**
     * POST  /bloqueds : Create a new bloqued.
     *
     * @param bloqued the bloqued to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bloqued, or with status 400 (Bad Request) if the bloqued has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bloqueds")
    @Timed
    public ResponseEntity<Bloqued> createBloqued(@RequestBody Bloqued bloqued) throws URISyntaxException {
        log.debug("REST request to save Bloqued : {}", bloqued);
        if (bloqued.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bloqued cannot already have an ID")).body(null);
        }
        Bloqued result = bloquedRepository.save(bloqued);
        return ResponseEntity.created(new URI("/api/bloqueds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bloqueds : Updates an existing bloqued.
     *
     * @param bloqued the bloqued to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bloqued,
     * or with status 400 (Bad Request) if the bloqued is not valid,
     * or with status 500 (Internal Server Error) if the bloqued couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bloqueds")
    @Timed
    public ResponseEntity<Bloqued> updateBloqued(@RequestBody Bloqued bloqued) throws URISyntaxException {
        log.debug("REST request to update Bloqued : {}", bloqued);
        if (bloqued.getId() == null) {
            return createBloqued(bloqued);
        }
        Bloqued result = bloquedRepository.save(bloqued);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bloqued.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bloqueds : get all the bloqueds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bloqueds in body
     */
    @GetMapping("/bloqueds")
    @Timed
    public List<Bloqued> getAllBloqueds() {
        log.debug("REST request to get all Bloqueds");
        List<Bloqued> bloqueds = bloquedRepository.findAll();
        return bloqueds;
    }

    /**
     * GET  /bloqueds/:id : get the "id" bloqued.
     *
     * @param id the id of the bloqued to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bloqued, or with status 404 (Not Found)
     */
    @GetMapping("/bloqueds/{id}")
    @Timed
    public ResponseEntity<Bloqued> getBloqued(@PathVariable Long id) {
        log.debug("REST request to get Bloqued : {}", id);
        Bloqued bloqued = bloquedRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bloqued));
    }

    /**
     * DELETE  /bloqueds/:id : delete the "id" bloqued.
     *
     * @param id the id of the bloqued to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bloqueds/{id}")
    @Timed
    public ResponseEntity<Void> deleteBloqued(@PathVariable Long id) {
        log.debug("REST request to delete Bloqued : {}", id);
        bloquedRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
