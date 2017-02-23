package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import proyecto.domain.User;
import proyecto.domain.UserExt;
import proyecto.repository.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SearchResource {

    private final Logger log = LoggerFactory.getLogger(PhotoResource.class);

    private UserRepository userRepository;

    private UserExtCriteriaRepository userExtCriteriaRepository;

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
            params.put("city",city);
        }
        if(points != null && points > 0){
            params.put("points",points);
        }
        if(tags != null){

        }
        if(validated){
            params.put("validated",validated);
        }
        if(ageMin > 0){
            params.put("agemin",ageMin);
        }
        if(ageMax > 0){
            params.put("agemax",ageMax);
        }

        /*List<UserExt> result = userExtCriteriaRepository.filterUserextDefinitions(params);

        return new ResponseEntity<>(
            result,
            HttpStatus.OK);*/
        return null;
    }

}
