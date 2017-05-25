package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import proyecto.domain.Following;

import proyecto.domain.User;
import proyecto.repository.FollowingRepository;
import proyecto.repository.UserRepository;
import proyecto.security.SecurityUtils;
import proyecto.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Following.
 */
@RestController
@RequestMapping("/api")
public class FollowingResource {

    private final Logger log = LoggerFactory.getLogger(FollowingResource.class);

    private static final String ENTITY_NAME = "following";

    private final FollowingRepository followingRepository;

    private final UserRepository userRepository;

    public FollowingResource(FollowingRepository followingRepository,UserRepository userRepository) {
        this.followingRepository = followingRepository;
        this.userRepository = userRepository;
    }

    /**
     * POST  /followings : Create a new following.
     *
     * @param following the following to create
     * @return the ResponseEntity with status 201 (Created) and with body the new following, or with status 400 (Bad Request) if the following has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/followings")
    @Timed
    public ResponseEntity<Following> createFollowing(@RequestBody Following following) throws URISyntaxException {
        log.debug("REST request to save Following : {}", following);
        if (following.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new following cannot already have an ID")).body(null);
        }
        Following result = followingRepository.save(following);
        return ResponseEntity.created(new URI("/api/followings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/Newfollowing/{id}")
    @Timed
    public ResponseEntity<Following> createNewFollowing(@PathVariable Long id) throws URISyntaxException {

        User followed = userRepository.findOne(id);
        User follower = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();

        Following following = followingRepository.findExistByFollowerAndFollowed(followed,follower);

        if(following != null){
            followingRepository.delete(following);

            return ResponseEntity.ok().headers(HeaderUtil.createAlert("deleted succesfuly","deleted succesfuly")).body(null);
        }else {
            following.setFollowed(followed);
            following.setFollower(follower);
            following.setTime(ZonedDateTime.now());
        }

        /*if (followed == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new following cannot already have an ID")).body(null);
        }*/
        Following result = followingRepository.save(following);
        return ResponseEntity.created(new URI("/api/followings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * PUT  /followings : Updates an existing following.
     *
     * @param following the following to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated following,
     * or with status 400 (Bad Request) if the following is not valid,
     * or with status 500 (Internal Server Error) if the following couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/followings")
    @Timed
    public ResponseEntity<Following> updateFollowing(@RequestBody Following following) throws URISyntaxException {
        log.debug("REST request to update Following : {}", following);
        if (following.getId() == null) {
            return createFollowing(following);
        }
        Following result = followingRepository.save(following);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, following.getId().toString()))
            .body(result);
    }

    /**
     * GET  /followings : get all the followings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of followings in body
     */
    @GetMapping("/followings")
    @Timed
    public List<Following> getAllFollowings() {
        log.debug("REST request to get all Followings");
        List<Following> followings = followingRepository.findAll();
        return followings;
    }

    // la gente q me sigue
    @GetMapping("/followers")
    @Timed
    public List<Following> getAllFollowers() {
        log.debug("REST request to get all Followings");
        List<Following> followings = followingRepository.findByFollowerIsCurrentUser();
        return followings;
    }

    // la gente khè sigo
    @GetMapping("/followed")
    @Timed
    public List<Following> getAllFollowed() {
        log.debug("REST request to get all Followings");
        List<Following> followings = followingRepository.findByFollowedIsCurrentUser();
        return followings;
    }

    // los followers de un perfil ajeno
    @GetMapping("/followers/{id}")
    @Timed
    public ResponseEntity<List<Following>> getFollowers(@PathVariable Long id) {
        log.debug("REST request to get Following : {}", id);

        User user = userRepository.findOne(id);
        List<Following> following = followingRepository.findByFollowersOneUser(user);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(following));
    }
    /**
     * GET  /followings/:id : get the "id" following.
     *
     * @param id the id of the following to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the following, or with status 404 (Not Found)
     */
    @GetMapping("/followings/{id}")
    @Timed
    public ResponseEntity<Following> getFollowing(@PathVariable Long id) {
        log.debug("REST request to get Following : {}", id);
        Following following = followingRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(following));
    }

    /**
     * DELETE  /followings/:id : delete the "id" following.
     *
     * @param id the id of the following to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/followings/{id}")
    @Timed
    public ResponseEntity<Void> deleteFollowing(@PathVariable Long id) {
        log.debug("REST request to delete Following : {}", id);
        followingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
