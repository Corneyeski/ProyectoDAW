package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/search/users")
    @Timed
    public List<User> searchUsers() throws URISyntaxException {



        return null;
    }

}
