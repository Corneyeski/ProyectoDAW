package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import proyecto.domain.User;
import proyecto.domain.UserProfileValoration;

import proyecto.repository.UserProfileValorationRepository;
import proyecto.repository.UserRepository;
import proyecto.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserProfileValoration.
 */
@RestController
@RequestMapping("/api")
public class UserProfileValorationResource {

    private final Logger log = LoggerFactory.getLogger(UserProfileValorationResource.class);

    private static final String ENTITY_NAME = "userProfileValoration";

    @Inject
    private UserProfileValorationRepository userProfileValorationRepository;

    @Inject
    private UserRepository userRepository;

    @PostMapping("/user-profile-valorations")
    @Timed
    public ResponseEntity<UserProfileValoration> createUserProfileValoration(@Valid @RequestBody UserProfileValoration userProfileValoration) throws URISyntaxException {
        log.debug("REST request to save UserProfileValoration : {}", userProfileValoration);
        if (userProfileValoration.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userProfileValoration cannot already have an ID")).body(null);
        }
        UserProfileValoration result = userProfileValorationRepository.save(userProfileValoration);
        return ResponseEntity.created(new URI("/api/user-profile-valorations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/user-profile-valorations")
    @Timed
    public ResponseEntity<UserProfileValoration> updateUserProfileValoration(@Valid @RequestBody UserProfileValoration userProfileValoration) throws URISyntaxException {
        log.debug("REST request to update UserProfileValoration : {}", userProfileValoration);
        if (userProfileValoration.getId() == null) {
            return createUserProfileValoration(userProfileValoration);
        }
        UserProfileValoration result = userProfileValorationRepository.save(userProfileValoration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userProfileValoration.getId().toString()))
            .body(result);
    }

    @GetMapping("/user-profile-valorations")
    @Timed
    public List<UserProfileValoration> getAllUserProfileValorations() {
        log.debug("REST request to get all UserProfileValorations");
        List<UserProfileValoration> userProfileValorations = userProfileValorationRepository.findAll();
        return userProfileValorations;
    }

    @GetMapping("/user-profile-valorations/{id}")
    @Timed
    public ResponseEntity<UserProfileValoration> getUserProfileValoration(@PathVariable Long id) {
        log.debug("REST request to get UserProfileValoration : {}", id);
        UserProfileValoration userProfileValoration = userProfileValorationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userProfileValoration));
    }

    @DeleteMapping("/user-profile-valorations/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserProfileValoration(@PathVariable Long id) {
        log.debug("REST request to delete UserProfileValoration : {}", id);
        userProfileValorationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    //TODO Añadir gestion de errores, respuestas

    @PutMapping("/setUpdateValoration/{vote}/{voted}/{value}")
    @Timed
    public ResponseEntity<Void> setUpdateValoration(@PathVariable Long vote, @PathVariable Long voted, @PathVariable int value) throws URISyntaxException {

        if (vote == voted || value < 0 || value > 5) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idSameUser", "No te puedes votar a ti mismo")).body(null);
        } else {

            User user = userRepository.findOne(vote);
            User userTwo = userRepository.findOne(voted);
            UserProfileValoration userProfileValoration = userProfileValorationRepository.findByValoradorAndValorado(user, userTwo);

            if (userProfileValoration != null) {
                userProfileValoration.setValue(value);
                userProfileValorationRepository.save(userProfileValoration);
            } else {
                userProfileValoration = new UserProfileValoration();
                userProfileValoration.setValorador(user);
                userProfileValoration.setValorado(userTwo);
                userProfileValoration.setValue(value);

                userProfileValorationRepository.save(userProfileValoration);
            }
            Double points = userProfileValorationRepository.avgUserPoints(userProfileValoration.getValorado());
            userTwo.getUserExt().setPopular(points);
            userRepository.save(userTwo);

            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, vote.toString())).build();
        }
    }

    /*@PostMapping("/setUpdateValorationPost/{vote}/{voted}")
    public ResponseEntity<Void> setUpdateValorationPost(@PathVariable Long vote, @PathVariable Long voted) throws URISyntaxException {

        if (vote == voted) {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, vote.toString())).build();
        } else {
            User user = userRepository.findOne(vote);
            User userTwo = userRepository.findOne(voted);

            if (userProfileValorationRepository.findByValoradorAndValorado(user, userTwo) != null) {

            }
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, vote.toString())).build();
        }
    }*/

}
