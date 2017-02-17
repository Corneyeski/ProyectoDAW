package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import proyecto.domain.User;
import proyecto.repository.OfferRepository;
import proyecto.repository.PhotoRepository;
import proyecto.repository.UserRepository;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Alan on 17/02/2017.
 */
@RestController
@RequestMapping("/api")
public class SearchResource {

    private final Logger log = LoggerFactory.getLogger(PhotoResource.class);

    private UserRepository userRepository;

    private PhotoRepository photoRepository;

    private OfferRepository offerRepository;

//    @PostMapping(value = "/search/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public List<User> searchUsers(@RequestParam String algo) throws URISyntaxException {
//
//
//
//        return null;
//    }

}
