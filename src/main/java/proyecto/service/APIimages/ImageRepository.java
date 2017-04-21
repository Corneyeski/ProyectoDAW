package proyecto.service.APIimages;

import org.springframework.stereotype.Repository;
import proyecto.service.dto.ImagesDTO;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

@Repository
public interface ImageRepository {

    @GET("images?fields=id,title,thumb,referral_destinations&sort_order=best")
    Call<ImagesDTO> getAllImages(
        @Header("Api-Key") String apiKey);

    public static String url = "https://api.gettyimages.com/v3/search/";
    public static final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}
