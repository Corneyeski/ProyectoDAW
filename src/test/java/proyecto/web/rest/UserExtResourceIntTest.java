package proyecto.web.rest;

import proyecto.ProyectoApp;

import proyecto.domain.UserExt;
import proyecto.repository.UserExtRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserExtResource REST controller.
 *
 * @see UserExtResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyectoApp.class)
public class UserExtResourceIntTest {

    private static final LocalDate DEFAULT_BIRTHDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PHONE = 1;
    private static final Integer UPDATED_PHONE = 2;

    private static final Integer DEFAULT_KIND = 1;
    private static final Integer UPDATED_KIND = 2;

    private static final String DEFAULT_TAGS = "AAAAAAAAAA";
    private static final String UPDATED_TAGS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Double DEFAULT_POPULAR = 1D;
    private static final Double UPDATED_POPULAR = 2D;

    private static final Double DEFAULT_COMPANY_POINTS = 1D;
    private static final Double UPDATED_COMPANY_POINTS = 2D;

    private static final Boolean DEFAULT_VALIDATED = false;
    private static final Boolean UPDATED_VALIDATED = true;

    @Autowired
    private UserExtRepository userExtRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restUserExtMockMvc;

    private UserExt userExt;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
            UserExtResource userExtResource = new UserExtResource(userExtRepository);
        this.restUserExtMockMvc = MockMvcBuilders.standaloneSetup(userExtResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExt createEntity(EntityManager em) {
        UserExt userExt = new UserExt()
                .birthdate(DEFAULT_BIRTHDATE)
                .phone(DEFAULT_PHONE)
                .kind(DEFAULT_KIND)
                .tags(DEFAULT_TAGS)
                .address(DEFAULT_ADDRESS)
                .country(DEFAULT_COUNTRY)
                .city(DEFAULT_CITY)
                .popular(DEFAULT_POPULAR)
                .companyPoints(DEFAULT_COMPANY_POINTS)
                .validated(DEFAULT_VALIDATED);
        return userExt;
    }

    @Before
    public void initTest() {
        userExt = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserExt() throws Exception {
        int databaseSizeBeforeCreate = userExtRepository.findAll().size();

        // Create the UserExt

        restUserExtMockMvc.perform(post("/api/user-exts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExt)))
            .andExpect(status().isCreated());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeCreate + 1);
        UserExt testUserExt = userExtList.get(userExtList.size() - 1);
        assertThat(testUserExt.getBirthdate()).isEqualTo(DEFAULT_BIRTHDATE);
        assertThat(testUserExt.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserExt.getKind()).isEqualTo(DEFAULT_KIND);
        assertThat(testUserExt.getTags()).isEqualTo(DEFAULT_TAGS);
        assertThat(testUserExt.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testUserExt.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testUserExt.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUserExt.getPopular()).isEqualTo(DEFAULT_POPULAR);
        assertThat(testUserExt.getCompanyPoints()).isEqualTo(DEFAULT_COMPANY_POINTS);
        assertThat(testUserExt.isValidated()).isEqualTo(DEFAULT_VALIDATED);
    }

    @Test
    @Transactional
    public void createUserExtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userExtRepository.findAll().size();

        // Create the UserExt with an existing ID
        UserExt existingUserExt = new UserExt();
        existingUserExt.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserExtMockMvc.perform(post("/api/user-exts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserExt)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserExts() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        // Get all the userExtList
        restUserExtMockMvc.perform(get("/api/user-exts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userExt.getId().intValue())))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].kind").value(hasItem(DEFAULT_KIND)))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].popular").value(hasItem(DEFAULT_POPULAR.doubleValue())))
            .andExpect(jsonPath("$.[*].companyPoints").value(hasItem(DEFAULT_COMPANY_POINTS.doubleValue())))
            .andExpect(jsonPath("$.[*].validated").value(hasItem(DEFAULT_VALIDATED.booleanValue())));
    }

    @Test
    @Transactional
    public void getUserExt() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        // Get the userExt
        restUserExtMockMvc.perform(get("/api/user-exts/{id}", userExt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userExt.getId().intValue()))
            .andExpect(jsonPath("$.birthdate").value(DEFAULT_BIRTHDATE.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.kind").value(DEFAULT_KIND))
            .andExpect(jsonPath("$.tags").value(DEFAULT_TAGS.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.popular").value(DEFAULT_POPULAR.doubleValue()))
            .andExpect(jsonPath("$.companyPoints").value(DEFAULT_COMPANY_POINTS.doubleValue()))
            .andExpect(jsonPath("$.validated").value(DEFAULT_VALIDATED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserExt() throws Exception {
        // Get the userExt
        restUserExtMockMvc.perform(get("/api/user-exts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserExt() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);
        int databaseSizeBeforeUpdate = userExtRepository.findAll().size();

        // Update the userExt
        UserExt updatedUserExt = userExtRepository.findOne(userExt.getId());
        updatedUserExt
                .birthdate(UPDATED_BIRTHDATE)
                .phone(UPDATED_PHONE)
                .kind(UPDATED_KIND)
                .tags(UPDATED_TAGS)
                .address(UPDATED_ADDRESS)
                .country(UPDATED_COUNTRY)
                .city(UPDATED_CITY)
                .popular(UPDATED_POPULAR)
                .companyPoints(UPDATED_COMPANY_POINTS)
                .validated(UPDATED_VALIDATED);

        restUserExtMockMvc.perform(put("/api/user-exts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserExt)))
            .andExpect(status().isOk());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeUpdate);
        UserExt testUserExt = userExtList.get(userExtList.size() - 1);
        assertThat(testUserExt.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testUserExt.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserExt.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testUserExt.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testUserExt.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testUserExt.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserExt.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserExt.getPopular()).isEqualTo(UPDATED_POPULAR);
        assertThat(testUserExt.getCompanyPoints()).isEqualTo(UPDATED_COMPANY_POINTS);
        assertThat(testUserExt.isValidated()).isEqualTo(UPDATED_VALIDATED);
    }

    @Test
    @Transactional
    public void updateNonExistingUserExt() throws Exception {
        int databaseSizeBeforeUpdate = userExtRepository.findAll().size();

        // Create the UserExt

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserExtMockMvc.perform(put("/api/user-exts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userExt)))
            .andExpect(status().isCreated());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserExt() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);
        int databaseSizeBeforeDelete = userExtRepository.findAll().size();

        // Get the userExt
        restUserExtMockMvc.perform(delete("/api/user-exts/{id}", userExt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserExt.class);
    }
}
