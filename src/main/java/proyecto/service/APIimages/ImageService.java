package proyecto.service.APIimages;

import org.springframework.stereotype.Service;
import proyecto.service.dto.Image;
import proyecto.service.dto.ImagesDTO;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alan on 21/04/2017.
 */
@Service
public class ImageService {

    public static final String apiKey = "59p6erya6yx4tkffyrb7cw4n";
    static ImageRepository apiService = ImageRepository.retrofit.create(ImageRepository.class);

    public static List<Image> getAllImages(){
        List<Image> keyWordsList = null;
        Call<ImagesDTO> callKeyWords = apiService.getAllImages(apiKey);
        try {
            keyWordsList = callKeyWords.execute().body().getImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyWordsList;
    }
}
