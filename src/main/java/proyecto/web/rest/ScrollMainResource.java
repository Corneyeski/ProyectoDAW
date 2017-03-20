package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import proyecto.domain.DTO.MainScrollDTO;
import proyecto.domain.Offer;
import proyecto.domain.Photo;
import proyecto.domain.UserExt;
import proyecto.repository.OfferRepository;
import proyecto.repository.PhotoRepository;
import proyecto.repository.UserExtRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScrollMainResource {

    @Inject
    UserExtRepository userExtRepository;

    @Inject
    OfferRepository offerRepository;

    @Inject
    PhotoRepository photoRepository;

    @RequestMapping(value = "/main/scroll",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<List<MainScrollDTO>> MainScroll(){

        //TODO obtener fotos donde la puntuacion del usuario sea mayor a 3

        List<Photo> photos = photoRepository.findUserPopularGreaterThan();

        List<Offer> offer = offerRepository.findOfferOrderByDate();

        //UserExt userExt = userExtRepository.findByUser(photos.get(1).getUser());

        List<MainScrollDTO> scroll = new ArrayList<>();

        int j = 0;
        for (int i = 0;i < photos.size(); i++){
            MainScrollDTO main = new MainScrollDTO();

            if(i % 4 == 0){
                main.setOffer(offer.get(j));
                j++;
            }else{
                main.setPhoto(photos.get(i));
                main.setUserExt(photos.get(i).getUser().getUserExt());
            }
            scroll.add(main);
        }

        return new ResponseEntity<>(
            scroll,
            HttpStatus.OK);
    }
}
