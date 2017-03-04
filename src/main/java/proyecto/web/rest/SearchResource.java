package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import liquibase.util.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.domain.User;
import proyecto.domain.UserExt;
import proyecto.repository.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SearchResource {

    private final Logger log = LoggerFactory.getLogger(PhotoResource.class);

    private UserRepository userRepository;

    @Inject
    UserExtCriteriaRepository userExtCriteriaRepository;

    private PhotoRepository photoRepository;

    private OfferRepository offerRepository;

    @RequestMapping(value = "/search/users",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<List<UserExt>> searchUsers(
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "minPoints", required = false) Double minPopular,
        @RequestParam(value = "maxPoints", required = false) Double maxPopular,
        @RequestParam(value = "tags", required = false) String tags,
        @RequestParam(value = "validated", required = false) boolean validated,
        @RequestParam(value = "ageMin", required = false) Integer ageMin,
        @RequestParam(value = "ageMax", required = false) Integer ageMax
    ) throws URISyntaxException {

        Map<String, Object> params = new HashMap<>();

        if (city != null && !city.equalsIgnoreCase("")) {
            params.put("city",city);
        }
        if(maxPopular != null && maxPopular > 0.0 && maxPopular > minPopular){
            params.put("maxPopular",maxPopular);
        }
        if(minPopular != null && minPopular > 0.0){
            params.put("minPopular",minPopular);
        }
        if(tags != null && !tags.equals("")){
            params.put("tags",tags);
        }
        if(validated){
            params.put("validated",validated);
        }
        if(ageMin != null && ageMin > 0){
            params.put("agemin",ageMin);
        }
        if(ageMax != null && ageMax > 0){
            params.put("agemax",ageMax);
        }

        List<UserExt> result = userExtCriteriaRepository.filterUserextDefinitions(params);

        return new ResponseEntity<>(
            result,
            HttpStatus.OK);
    }

}
