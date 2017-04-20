package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proyecto.domain.Photo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
public class DataGenerator {

    @RequestMapping(value = "/DataGenerator",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<List<String>> searchPhoto() throws URISyntaxException {

        try {
            JSONObject json = new JSONObject(IOUtils.toString(new URL("https://api.gettyimages.com/v3/search/images?fields=id,title,thumb,referral_destinations&sort_order=best"), Charset.forName("UTF-8")));

            System.out.println(json);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
