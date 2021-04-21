package br.com.gsividal.stoomproject.service.geocoding;

import br.com.gsividal.stoomproject.service.geocoding.dto.GeocodingResponse;
import br.com.gsividal.stoomproject.service.geocoding.dto.Geometry;
import br.com.gsividal.stoomproject.service.geocoding.dto.Location;
import br.com.gsividal.stoomproject.service.geocoding.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Optional;

@Component
@Slf4j
public class GeocodingService {

    private final RestTemplate client;

    @Value("${google.geocoding-key}")
    private String geoCodingKey;

    private final String SERVICE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={key}"; //?address=Winnetka&key=

    public GeocodingService(RestTemplateBuilder restTemplateBuilder) {
        this.client = restTemplateBuilder
                .build();
    }

    public Coordinates getCoordinates(String streetName, Long number, String neighbourhood, String city, String state, String country) {
        String address = String.format("%s, %d, %s, %s, %s, %s", streetName, number, neighbourhood, city, state, country);

        ResponseEntity<GeocodingResponse> responseEntity = client.getForEntity(SERVICE_URL, GeocodingResponse.class, address, geoCodingKey);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            GeocodingResponse body = responseEntity.getBody();
            log.debug("body: {}", body);
            Location location = Optional.ofNullable(body)
                    .map(GeocodingResponse::getResults)
                    .stream()
                    .flatMap(Collection::stream)
                    .findFirst()
                    .map(Result::getGeometry)
                    .map(Geometry::getLocation)
                    .orElseThrow(() -> new IllegalArgumentException("Could not fetch results for address: " + address));
            return new Coordinates(location.getLat().floatValue(), location.getLng().floatValue());
        } else {
            throw new IllegalArgumentException("Could not fetch results for address: " + address);
        }
    }
}
