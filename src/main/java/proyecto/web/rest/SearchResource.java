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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/search/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<User> searchUsers(
        @RequestParam(value = "userName", required = false) String userName,
        @RequestParam(value = "City", required = false) String city,
        @RequestParam(value = "points", required = false) Double points,
        @RequestParam(value = "tags", required = false) String tags,
        @RequestParam(value = "validated", required = false) boolean validated,
        @RequestParam(value = "ageMin", required = false) int ageMin,
        @RequestParam(value = "ageMax", required = false) int ageMax
    ) throws URISyntaxException {

        Map<String, Object> params = new HashMap<>();

        if (userName != null) {
            params.put("username", userName);
        }
        if(city != null){

        }
        return null;
    }

}
