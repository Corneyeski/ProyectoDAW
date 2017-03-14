package proyecto.domain.DTO;

import proyecto.domain.Offer;
import proyecto.domain.Photo;
import proyecto.domain.UserExt;

public class MainScrollDTO {

    private UserExt userExt;

    private Photo photo;

    private Offer offer;

    public MainScrollDTO() {}

    public MainScrollDTO(UserExt userExt, Photo photo, Offer offer) {

        this.userExt = userExt;
        this.photo = photo;
        this.offer = offer;
    }
    public UserExt getUserExt() { return userExt; }

    public void setUserExt(UserExt userExt) {this.userExt = userExt; }

    public Photo getPhoto() {return photo;}

    public void setPhoto(Photo photo) {this.photo = photo;}

    public Offer getOffer() {return offer;}

    public void setOffer(Offer offer) {this.offer = offer;}
}
