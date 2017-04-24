package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import proyecto.domain.Photo;
import proyecto.domain.User;
import proyecto.domain.UserExt;
import proyecto.repository.OfferRepository;
import proyecto.repository.PhotoRepository;
import proyecto.repository.UserExtRepository;
import proyecto.repository.UserRepository;
import proyecto.service.APIimages.ImageService;
import proyecto.service.dto.Image;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api")
public class DataGenerator {

    @Inject
    ImageService imageService;

    @Inject
    UserExtRepository userExtRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    OfferRepository offerRepository;

    @Inject
    PhotoRepository photoRepository;

    @RequestMapping(value = "/DataGenerator",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<List<String>> searchPhoto() throws URISyntaxException {

        List<Image> images = imageService.getAllImages();

        List<User> users = userRepository.findAll();

        /*for(int i = 0;i < 100;i++){

            User user = new User();

            int ran = 0 + (int) (Math.random() * 15);
            String ranname = "";
            for (int j = 0; j < ran; j++) {
                Random r = new Random();
                ranname += (char) (r.nextInt(26) + 'a');
            }
            user.setFirstName(ranname);
            user.setPassword("stucom");

            ran = 0 + (int) (Math.random() * 15);
            ranname = "";
            for (int j = 0; j < ran; j++) {
                Random r = new Random();
                ranname += (char) (r.nextInt(26) + 'a');
            }
            ranname += "@";
            for (int j = 0; j < ran; j++) {
                Random r = new Random();
                ranname += (char) (r.nextInt(26) + 'a');
            }
            user.setEmail(ranname);

            ran = 0 + (int) (Math.random() * 15);
            ranname = "";
            for (int j = 0; j < ran; j++) {
                Random r = new Random();
                ranname += (char) (r.nextInt(26) + 'a');
            }
            user.setLangKey("es");
            user.setLogin(ranname);
            user.setActivated(true);
            user.setActivationKey("");
            user.setImageUrl("");
            user.setLastName("");
            user.setResetKey("stucom");
            user.setResetDate(ZonedDateTime.now());
            userRepository.save(user);
        }*/


        int cont = 0;
        do {
            for (User u : users) {
                UserExt userExt = new UserExt();

                int ran = 0 + (int) (Math.random() * 15);
                String ranname = "";
                for (int i = 0; i < ran; i++) {
                    Random r = new Random();
                    ranname += (char) (r.nextInt(26) + 'a');
                }
                userExt.setAddress(ranname);

                ran = 9 + (int) (Math.random() * 15);
                ranname = "";
                for (int i = 0; i < ran; i++) {
                    Random r = new Random();
                    ranname += (char) (r.nextInt(26) + 'a');
                }
                userExt.setCity(ranname);

                ran = 9 + (int) (Math.random() * 15);
                ranname = "";
                for (int i = 0; i < ran; i++) {
                    Random r = new Random();
                    ranname += (char) (r.nextInt(26) + 'a');
                }
                userExt.setCountry(ranname);


                ran = 9 + (int) (Math.random() * 15);
                ranname = "";
                for (int i = 0; i < ran; i++) {
                    Random r = new Random();
                    ranname += (char) (r.nextInt(26) + 'a');
                }
                userExt.setKind(0 + (int) (Math.random() * 3));
                userExt.setUser(u);

                userExtRepository.save(userExt);

                Photo p = new Photo();
                p.setUser(u);
                p.setUrl(images.get(cont).getReferralDestinations().get(0).getSiteName());
                p.setName(images.get(cont).getTitle());
                Random r = new Random();
                double d = -0 + r.nextDouble() * 5.0;
                p.setPoints(d);

                photoRepository.save(p);

                cont++;
            }
        }while(cont < 50);

        return null;
    }
}