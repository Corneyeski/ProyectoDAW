package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PhotoValoration.
 */
@Entity
@Table(name = "photo_valoration")
public class PhotoValoration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark")
    private Double mark;

    @Column(name = "time")
    private ZonedDateTime time;

    @ManyToOne
    private Photo photo;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMark() {
        return mark;
    }

    public PhotoValoration mark(Double mark) {
        this.mark = mark;
        return this;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public PhotoValoration time(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Photo getPhoto() {
        return photo;
    }

    public PhotoValoration photo(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public PhotoValoration user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhotoValoration photoValoration = (PhotoValoration) o;
        if (photoValoration.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, photoValoration.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PhotoValoration{" +
            "id=" + id +
            ", mark='" + mark + "'" +
            ", time='" + time + "'" +
            '}';
    }
}
