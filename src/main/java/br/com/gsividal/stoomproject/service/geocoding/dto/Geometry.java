
package br.com.gsividal.stoomproject.service.geocoding.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "location",
    "location_type",
    "viewport"
})
@Generated("jsonschema2pojo")
@Data
public class Geometry {

    @JsonProperty("location")
    public Location location;
    @JsonProperty("location_type")
    public String locationType;
    @JsonProperty("viewport")
    public Viewport viewport;

}
