package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Offer.
 */
@Entity
@Table(name = "offer")
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private ZonedDateTime time;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "time_job")
    private Double timeJob;

    @Column(name = "closed")
    private Boolean closed;

    @Column(name = "tags")
    private String tags;

    @Column(name = "location")
    private String location;

    @ManyToMany
    @JoinTable(name = "offer_user",
               joinColumns = @JoinColumn(name="offers_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="users_id", referencedColumnName="id"))
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Offer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Offer description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Offer time(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Integer getSalary() {
        return salary;
    }

    public Offer salary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Double getTimeJob() {
        return timeJob;
    }

    public Offer timeJob(Double timeJob) {
        this.timeJob = timeJob;
        return this;
    }

    public void setTimeJob(Double timeJob) {
        this.timeJob = timeJob;
    }

    public Boolean isClosed() {
        return closed;
    }

    public Offer closed(Boolean closed) {
        this.closed = closed;
        return this;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getTags() {
        return tags;
    }

    public Offer tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLocation() {
        return location;
    }

    public Offer location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Offer users(Set<User> users) {
        this.users = users;
        return this;
    }

    public Offer addUser(User user) {
        this.users.add(user);
        return this;
    }

    public Offer removeUser(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Offer offer = (Offer) o;
        if (offer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, offer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Offer{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", time='" + time + "'" +
            ", salary='" + salary + "'" +
            ", timeJob='" + timeJob + "'" +
            ", closed='" + closed + "'" +
            ", tags='" + tags + "'" +
            ", location='" + location + "'" +
            '}';
    }
}
