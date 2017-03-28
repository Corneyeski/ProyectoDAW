package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Photo.
 */
@Entity
@Table(name = "photo")
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "url")
    private String url;

    @Column(name = "time")
    private ZonedDateTime time;

    @Column(name = "tags")
    private String tags;

    @Column(name = "points")
    private Double points;

    @ManyToOne
    private User user;

    @ManyToOne
    private Album album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Photo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public Photo image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Photo imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getUrl() {
        return url;
    }

    public Photo url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Photo time(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public String getTags() {
        return tags;
    }

    public Photo tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Double getPoints() {
        return points;
    }

    public Photo points(Double points) {
        this.points = points;
        return this;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public Photo user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Album getAlbum() {
        return album;
    }

    public Photo album(Album album) {
        this.album = album;
        return this;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photo photo = (Photo) o;
        if (photo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, photo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Photo{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", image='" + image + "'" +
            ", imageContentType='" + imageContentType + "'" +
            ", url='" + url + "'" +
            ", time='" + time + "'" +
            ", tags='" + tags + "'" +
            ", points='" + points + "'" +
            '}';
    }
}
