
package br.com.gsividal.stoomproject.service.geocoding.dto;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address_components",
    "formatted_address",
    "geometry",
    "place_id",
    "plus_code",
    "types"
})
@Generated("jsonschema2pojo")
@Data
public class Result {

    @JsonProperty("address_components")
    public List<AddressComponent> addressComponents = null;
    @JsonProperty("formatted_address")
    public String formattedAddress;
    @JsonProperty("geometry")
    public Geometry geometry;
    @JsonProperty("place_id")
    public String placeId;
    @JsonProperty("plus_code")
    public PlusCode plusCode;
    @JsonProperty("types")
    public List<String> types = null;

}
