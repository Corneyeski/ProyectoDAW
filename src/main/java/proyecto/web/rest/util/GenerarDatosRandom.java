package proyecto.web.rest.util;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import proyecto.domain.User;
import proyecto.domain.UserExt;
import proyecto.repository.OfferRepository;
import proyecto.repository.UserExtRepository;
import proyecto.repository.UserRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

/**
 * Created by Alan on 13/03/2017.
 */
public class GenerarDatosRandom {

    @Inject
    UserExtRepository userExtRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    OfferRepository offerRepository;

    public void generate(){

        List<User> users = userRepository.findAll();

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
                userExt.setPhone(Integer.parseInt(ranname));

                userExt.setKind(0 + (int) (Math.random() * 3));

                userExt.setUser(u);

                userExtRepository.save(userExt);
                cont++;
            }
        }while(cont < 50);

        try {
            JSONObject json = new JSONObject(IOUtils.toString(new URL("https://api.gettyimages.com/v3/search/images?fields=id,title,thumb,referral_destinations&sort_order=best"), Charset.forName("UTF-8")));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
