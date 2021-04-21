
package br.com.gsividal.stoomproject.service.geocoding.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "northeast",
    "southwest"
})
@Generated("jsonschema2pojo")
@Data
public class Viewport {

    @JsonProperty("northeast")
    public Northeast northeast;
    @JsonProperty("southwest")
    public Southwest southwest;

}
