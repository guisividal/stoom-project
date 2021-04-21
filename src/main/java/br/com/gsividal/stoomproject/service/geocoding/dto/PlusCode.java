
package br.com.gsividal.stoomproject.service.geocoding.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "compound_code",
    "global_code"
})
@Generated("jsonschema2pojo")
@Data
public class PlusCode {

    @JsonProperty("compound_code")
    public String compoundCode;
    @JsonProperty("global_code")
    public String globalCode;

}
