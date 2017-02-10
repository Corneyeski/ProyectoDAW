package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Connection.
 */
@Entity
@Table(name = "connection")
public class Connection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acepted")
    private Boolean acepted;

    @Column(name = "text")
    private String text;

    @Column(name = "time")
    private ZonedDateTime time;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAcepted() {
        return acepted;
    }

    public Connection acepted(Boolean acepted) {
        this.acepted = acepted;
        return this;
    }

    public void setAcepted(Boolean acepted) {
        this.acepted = acepted;
    }

    public String getText() {
        return text;
    }

    public Connection text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Connection time(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public Connection user(User user) {
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
        Connection connection = (Connection) o;
        if (connection.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, connection.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Connection{" +
            "id=" + id +
            ", acepted='" + acepted + "'" +
            ", text='" + text + "'" +
            ", time='" + time + "'" +
            '}';
    }
}
