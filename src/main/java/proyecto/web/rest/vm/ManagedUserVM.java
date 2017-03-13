package proyecto.web.rest.vm;

import proyecto.service.dto.UserDTO;

import javax.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    private Integer phone;

    private Integer kind;

    private String tags;

    private String address;

    private String country;

    private String city;

    private Double popular;

    private Double companyPoints;

    private Boolean validated;

    private Integer age;

    private ZonedDateTime birthday;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(Long id, String login, String password, String firstName, String lastName,
                         String email, boolean activated, String imageUrl, String langKey,
                         String createdBy, ZonedDateTime createdDate, String lastModifiedBy, ZonedDateTime lastModifiedDate,
                         Set<String> authorities) {

        super(id, login, firstName, lastName, email, activated, imageUrl, langKey,
            createdBy, createdDate, lastModifiedBy, lastModifiedDate, authorities);

        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public Integer getPhone() {
        return phone;
    }

    public Integer getKind() {
        return kind;
    }

    public String getTags() {
        return tags;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Double getPopular() {
        return popular;
    }

    public Double getCompanyPoints() {
        return companyPoints;
    }

    public Boolean getValidated() {
        return validated;
    }

    public Integer getAge() {
        return age;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
