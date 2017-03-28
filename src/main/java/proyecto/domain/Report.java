package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    private User photo;

    @ManyToOne
    private User offer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Report title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public Report message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public Report user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getPhoto() {
        return photo;
    }

    public Report photo(User user) {
        this.photo = user;
        return this;
    }

    public void setPhoto(User user) {
        this.photo = user;
    }

    public User getOffer() {
        return offer;
    }

    public Report offer(User user) {
        this.offer = user;
        return this;
    }

    public void setOffer(User user) {
        this.offer = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        if (report.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", message='" + message + "'" +
            '}';
    }
}
