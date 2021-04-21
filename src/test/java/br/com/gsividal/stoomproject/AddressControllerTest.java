package br.com.gsividal.stoomproject;

import br.com.gsividal.stoomproject.dto.AddressDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnNotFoundOnNotExistingAddress() {
        ResponseEntity<AddressDTO> entity = this.restTemplate.getForEntity("http://localhost:" + port + "/addresses/99", AddressDTO.class);
        assertThat(entity.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldDeleteAddress() {
        // CREATE
        AddressDTO addressDTO = createAddress(3L, 0f, 0f);
        ResponseEntity<AddressDTO> entity = this.restTemplate.postForEntity("http://localhost:" + port + "/addresses", addressDTO, AddressDTO.class);
        assertThat(entity.getBody()).isNotNull();

        // DELETE
        this.restTemplate.delete("http://localhost:" + port + "/addresses/{id}", Map.of("id", entity.getBody().getId()));

        // GET
        ResponseEntity<AddressDTO> notFoundEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/addresses/{id}",
                AddressDTO.class, Map.of("id", entity.getBody().getId()));
        assertThat(notFoundEntity.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldCreateAddress() {
        // CREATE
        AddressDTO addressDTO = createAddress(2L, 12f, 15f);
        ResponseEntity<AddressDTO> entity = this.restTemplate.postForEntity("http://localhost:" + port + "/addresses", addressDTO, AddressDTO.class);

        assertThat(entity.getStatusCode()).isEqualTo(CREATED);
        assertThat(entity.getBody()).isNotNull();
        assertThat(entity.getBody().getLatitude()).isEqualTo(12f);
        assertThat(entity.getBody().getLongitude()).isEqualTo(15f);

        // GET
        ResponseEntity<AddressDTO> getEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/addresses/{id}", AddressDTO.class,
                Map.of("id", entity.getBody().getId()));
        assertThat(getEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void shouldCreateAddressUsingGeocodingAPI() {
        // CREATE
        AddressDTO addressDTO = createAddress(1L, null, null);
        ResponseEntity<AddressDTO> entity = this.restTemplate.postForEntity("http://localhost:" + port + "/addresses", addressDTO, AddressDTO.class);

        assertThat(entity.getStatusCode()).isEqualTo(CREATED);
        assertThat(entity.getBody()).isNotNull();
        assertThat(entity.getBody().getLatitude()).isEqualTo(-22.733562f);
        assertThat(entity.getBody().getLongitude()).isEqualTo(-46.892284f);

        // GET
        ResponseEntity<AddressDTO> getEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/addresses/{id}", AddressDTO.class,
                Map.of("id", entity.getBody().getId()));
        assertThat(getEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void shouldUpdateAddress() {
        // CREATE
        AddressDTO addressDTO = createAddress(4L, 12f, 15f);
        ResponseEntity<AddressDTO> entity = this.restTemplate.postForEntity("http://localhost:" + port + "/addresses", addressDTO, AddressDTO.class);
        assertThat(entity.getStatusCode()).isEqualTo(CREATED);
        assertThat(entity.getBody()).isNotNull();

        // CHANGE VALUE
        AddressDTO address = entity.getBody();
        address.setComplement("Apartamento");

        // UPDATE
        this.restTemplate.put("http://localhost:" + port + "/addresses", address, AddressDTO.class);

        // GET
        ResponseEntity<AddressDTO> updatedAddress = this.restTemplate.getForEntity("http://localhost:" + port + "/addresses/{id}",
                AddressDTO.class, Map.of("id", address.getId()));

        assertThat(updatedAddress.getBody()).isNotNull();
        assertThat(updatedAddress.getBody().getComplement()).isEqualTo("Apartamento");
    }

    private AddressDTO createAddress(Long id, Float lat, Float lng) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(id);
        addressDTO.setCity("Pedreira");
        addressDTO.setCountry("Brasil");
        addressDTO.setNeighbourhood("Morumbi");
        addressDTO.setNumber(105);
        addressDTO.setState("SP");
        addressDTO.setZipcode("13920-000");
        addressDTO.setStreetName("Rua Louis Pasteur");

        addressDTO.setLatitude(lat);
        addressDTO.setLongitude(lng);
        addressDTO.setComplement("Casa");
        return addressDTO;
    }

}
