package proyecto.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A UserExt.
 */
@Entity
@Table(name = "user_ext")
public class UserExt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "kind")
    private Integer kind;

    @Column(name = "tags")
    private String tags;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "popular")
    private Double popular;

    @Column(name = "company_points")
    private Double companyPoints;

    @Column(name = "validated")
    private Boolean validated;

    @Column(name = "age")
    private Integer age;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public UserExt birthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getPhone() {
        return phone;
    }

    public UserExt phone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getKind() {
        return kind;
    }

    public UserExt kind(Integer kind) {
        this.kind = kind;
        return this;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getTags() {
        return tags;
    }

    public UserExt tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAddress() {
        return address;
    }

    public UserExt address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public UserExt country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public UserExt city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getPopular() {
        return popular;
    }

    public UserExt popular(Double popular) {
        this.popular = popular;
        return this;
    }

    public void setPopular(Double popular) {
        this.popular = popular;
    }

    public Double getCompanyPoints() {
        return companyPoints;
    }

    public UserExt companyPoints(Double companyPoints) {
        this.companyPoints = companyPoints;
        return this;
    }

    public void setCompanyPoints(Double companyPoints) {
        this.companyPoints = companyPoints;
    }

    public Boolean isValidated() {
        return validated;
    }

    public UserExt validated(Boolean validated) {
        this.validated = validated;
        return this;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public Integer getAge() {
        return age;
    }

    public UserExt age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public UserExt user(User user) {
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
        UserExt userExt = (UserExt) o;
        if (userExt.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userExt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserExt{" +
            "id=" + id +
            ", birthdate='" + birthdate + "'" +
            ", phone='" + phone + "'" +
            ", kind='" + kind + "'" +
            ", tags='" + tags + "'" +
            ", address='" + address + "'" +
            ", country='" + country + "'" +
            ", city='" + city + "'" +
            ", popular='" + popular + "'" +
            ", companyPoints='" + companyPoints + "'" +
            ", validated='" + validated + "'" +
            ", age='" + age + "'" +
            '}';
    }
}
