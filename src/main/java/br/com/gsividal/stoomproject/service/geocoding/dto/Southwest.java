
package br.com.gsividal.stoomproject.service.geocoding.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lat",
    "lng"
})
@Generated("jsonschema2pojo")
@Data
public class Southwest {

    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lng")
    public Double lng;

}
