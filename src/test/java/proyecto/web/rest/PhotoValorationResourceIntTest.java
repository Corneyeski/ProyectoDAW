package proyecto.web.rest;

import proyecto.ProyectoApp;

import proyecto.domain.PhotoValoration;
import proyecto.repository.PhotoValorationRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static proyecto.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PhotoValorationResource REST controller.
 *
 * @see PhotoValorationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyectoApp.class)
public class PhotoValorationResourceIntTest {

    private static final Double DEFAULT_MARK = 1D;
    private static final Double UPDATED_MARK = 2D;

    private static final ZonedDateTime DEFAULT_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private PhotoValorationRepository photoValorationRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restPhotoValorationMockMvc;

    private PhotoValoration photoValoration;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
            PhotoValorationResource photoValorationResource = new PhotoValorationResource(photoValorationRepository);
        this.restPhotoValorationMockMvc = MockMvcBuilders.standaloneSetup(photoValorationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhotoValoration createEntity(EntityManager em) {
        PhotoValoration photoValoration = new PhotoValoration()
                .mark(DEFAULT_MARK)
                .time(DEFAULT_TIME);
        return photoValoration;
    }

    @Before
    public void initTest() {
        photoValoration = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhotoValoration() throws Exception {
        int databaseSizeBeforeCreate = photoValorationRepository.findAll().size();

        // Create the PhotoValoration

        restPhotoValorationMockMvc.perform(post("/api/photo-valorations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photoValoration)))
            .andExpect(status().isCreated());

        // Validate the PhotoValoration in the database
        List<PhotoValoration> photoValorationList = photoValorationRepository.findAll();
        assertThat(photoValorationList).hasSize(databaseSizeBeforeCreate + 1);
        PhotoValoration testPhotoValoration = photoValorationList.get(photoValorationList.size() - 1);
        assertThat(testPhotoValoration.getMark()).isEqualTo(DEFAULT_MARK);
        assertThat(testPhotoValoration.getTime()).isEqualTo(DEFAULT_TIME);
    }

    @Test
    @Transactional
    public void createPhotoValorationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = photoValorationRepository.findAll().size();

        // Create the PhotoValoration with an existing ID
        PhotoValoration existingPhotoValoration = new PhotoValoration();
        existingPhotoValoration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhotoValorationMockMvc.perform(post("/api/photo-valorations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPhotoValoration)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PhotoValoration> photoValorationList = photoValorationRepository.findAll();
        assertThat(photoValorationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPhotoValorations() throws Exception {
        // Initialize the database
        photoValorationRepository.saveAndFlush(photoValoration);

        // Get all the photoValorationList
        restPhotoValorationMockMvc.perform(get("/api/photo-valorations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(photoValoration.getId().intValue())))
            .andExpect(jsonPath("$.[*].mark").value(hasItem(DEFAULT_MARK.doubleValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(sameInstant(DEFAULT_TIME))));
    }

    @Test
    @Transactional
    public void getPhotoValoration() throws Exception {
        // Initialize the database
        photoValorationRepository.saveAndFlush(photoValoration);

        // Get the photoValoration
        restPhotoValorationMockMvc.perform(get("/api/photo-valorations/{id}", photoValoration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(photoValoration.getId().intValue()))
            .andExpect(jsonPath("$.mark").value(DEFAULT_MARK.doubleValue()))
            .andExpect(jsonPath("$.time").value(sameInstant(DEFAULT_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingPhotoValoration() throws Exception {
        // Get the photoValoration
        restPhotoValorationMockMvc.perform(get("/api/photo-valorations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhotoValoration() throws Exception {
        // Initialize the database
        photoValorationRepository.saveAndFlush(photoValoration);
        int databaseSizeBeforeUpdate = photoValorationRepository.findAll().size();

        // Update the photoValoration
        PhotoValoration updatedPhotoValoration = photoValorationRepository.findOne(photoValoration.getId());
        updatedPhotoValoration
                .mark(UPDATED_MARK)
                .time(UPDATED_TIME);

        restPhotoValorationMockMvc.perform(put("/api/photo-valorations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPhotoValoration)))
            .andExpect(status().isOk());

        // Validate the PhotoValoration in the database
        List<PhotoValoration> photoValorationList = photoValorationRepository.findAll();
        assertThat(photoValorationList).hasSize(databaseSizeBeforeUpdate);
        PhotoValoration testPhotoValoration = photoValorationList.get(photoValorationList.size() - 1);
        assertThat(testPhotoValoration.getMark()).isEqualTo(UPDATED_MARK);
        assertThat(testPhotoValoration.getTime()).isEqualTo(UPDATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingPhotoValoration() throws Exception {
        int databaseSizeBeforeUpdate = photoValorationRepository.findAll().size();

        // Create the PhotoValoration

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPhotoValorationMockMvc.perform(put("/api/photo-valorations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photoValoration)))
            .andExpect(status().isCreated());

        // Validate the PhotoValoration in the database
        List<PhotoValoration> photoValorationList = photoValorationRepository.findAll();
        assertThat(photoValorationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePhotoValoration() throws Exception {
        // Initialize the database
        photoValorationRepository.saveAndFlush(photoValoration);
        int databaseSizeBeforeDelete = photoValorationRepository.findAll().size();

        // Get the photoValoration
        restPhotoValorationMockMvc.perform(delete("/api/photo-valorations/{id}", photoValoration.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PhotoValoration> photoValorationList = photoValorationRepository.findAll();
        assertThat(photoValorationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhotoValoration.class);
    }
}
