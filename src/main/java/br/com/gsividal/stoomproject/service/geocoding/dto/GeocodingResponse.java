
package br.com.gsividal.stoomproject.service.geocoding.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeocodingResponse {

    public List<Result> results = null;
}