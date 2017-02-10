package proyecto.web.rest;

import com.codahale.metrics.annotation.Timed;
import proyecto.domain.Connection;

import proyecto.repository.ConnectionRepository;
import proyecto.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Connection.
 */
@RestController
@RequestMapping("/api")
public class ConnectionResource {

    private final Logger log = LoggerFactory.getLogger(ConnectionResource.class);

    private static final String ENTITY_NAME = "connection";
        
    private final ConnectionRepository connectionRepository;

    public ConnectionResource(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    /**
     * POST  /connections : Create a new connection.
     *
     * @param connection the connection to create
     * @return the ResponseEntity with status 201 (Created) and with body the new connection, or with status 400 (Bad Request) if the connection has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/connections")
    @Timed
    public ResponseEntity<Connection> createConnection(@RequestBody Connection connection) throws URISyntaxException {
        log.debug("REST request to save Connection : {}", connection);
        if (connection.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new connection cannot already have an ID")).body(null);
        }
        Connection result = connectionRepository.save(connection);
        return ResponseEntity.created(new URI("/api/connections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /connections : Updates an existing connection.
     *
     * @param connection the connection to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated connection,
     * or with status 400 (Bad Request) if the connection is not valid,
     * or with status 500 (Internal Server Error) if the connection couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/connections")
    @Timed
    public ResponseEntity<Connection> updateConnection(@RequestBody Connection connection) throws URISyntaxException {
        log.debug("REST request to update Connection : {}", connection);
        if (connection.getId() == null) {
            return createConnection(connection);
        }
        Connection result = connectionRepository.save(connection);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, connection.getId().toString()))
            .body(result);
    }

    /**
     * GET  /connections : get all the connections.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of connections in body
     */
    @GetMapping("/connections")
    @Timed
    public List<Connection> getAllConnections() {
        log.debug("REST request to get all Connections");
        List<Connection> connections = connectionRepository.findAll();
        return connections;
    }

    /**
     * GET  /connections/:id : get the "id" connection.
     *
     * @param id the id of the connection to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the connection, or with status 404 (Not Found)
     */
    @GetMapping("/connections/{id}")
    @Timed
    public ResponseEntity<Connection> getConnection(@PathVariable Long id) {
        log.debug("REST request to get Connection : {}", id);
        Connection connection = connectionRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(connection));
    }

    /**
     * DELETE  /connections/:id : delete the "id" connection.
     *
     * @param id the id of the connection to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/connections/{id}")
    @Timed
    public ResponseEntity<Void> deleteConnection(@PathVariable Long id) {
        log.debug("REST request to delete Connection : {}", id);
        connectionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
