
package br.com.gsividal.stoomproject.service.geocoding.dto;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "results",
    "status"
})
@Generated("jsonschema2pojo")
@Data
public class GeocodingResponse {

    @JsonProperty("results")
    public List<Result> results = null;
    @JsonProperty("status")
    public String status;

}
