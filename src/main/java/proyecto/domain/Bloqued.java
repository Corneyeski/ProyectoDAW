package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Bloqued.
 */
@Entity
@Table(name = "bloqued")
public class Bloqued implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User block;

    @ManyToOne
    private User blocked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBlock() {
        return block;
    }

    public Bloqued block(User user) {
        this.block = user;
        return this;
    }

    public void setBlock(User user) {
        this.block = user;
    }

    public User getBlocked() {
        return blocked;
    }

    public Bloqued blocked(User user) {
        this.blocked = user;
        return this;
    }

    public void setBlocked(User user) {
        this.blocked = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bloqued bloqued = (Bloqued) o;
        if (bloqued.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, bloqued.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Bloqued{" +
            "id=" + id +
            '}';
    }
}
