package proyecto.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A UserProfileValoration.
 */
@Entity
@Table(name = "user_profile_valoration")
public class UserProfileValoration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    private User valorador;

    @ManyToOne
    private User valorado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public UserProfileValoration value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getComments() {
        return comments;
    }

    public UserProfileValoration comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getValorador() {
        return valorador;
    }

    public UserProfileValoration valorador(User user) {
        this.valorador = user;
        return this;
    }

    public void setValorador(User user) {
        this.valorador = user;
    }

    public User getValorado() {
        return valorado;
    }

    public UserProfileValoration valorado(User user) {
        this.valorado = user;
        return this;
    }

    public void setValorado(User user) {
        this.valorado = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserProfileValoration userProfileValoration = (UserProfileValoration) o;
        if (userProfileValoration.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userProfileValoration.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserProfileValoration{" +
            "id=" + id +
            ", value='" + value + "'" +
            ", comments='" + comments + "'" +
            '}';
    }
}
