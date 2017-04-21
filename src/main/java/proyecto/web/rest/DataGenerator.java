package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import proyecto.service.APIimages.ImageService;
import proyecto.service.dto.Image;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class DataGenerator {

    @Inject
    ImageService imageService;

    @RequestMapping(value = "/DataGenerator",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<List<String>> searchPhoto() throws URISyntaxException {

        List<Image> images = imageService.getAllImages();



        return null;
    }
}
