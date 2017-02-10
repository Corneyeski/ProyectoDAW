package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Following.
 */
@Entity
@Table(name = "following")
public class Following implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time")
    private ZonedDateTime time;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User followed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Following time(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public User getFollower() {
        return follower;
    }

    public Following follower(User user) {
        this.follower = user;
        return this;
    }

    public void setFollower(User user) {
        this.follower = user;
    }

    public User getFollowed() {
        return followed;
    }

    public Following followed(User user) {
        this.followed = user;
        return this;
    }

    public void setFollowed(User user) {
        this.followed = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Following following = (Following) o;
        if (following.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, following.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Following{" +
            "id=" + id +
            ", time='" + time + "'" +
            '}';
    }
}
