package br.com.gsividal.stoomproject.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private long id;
    private long number;

    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String streetName;
    private String neighbourhood;

    private Float latitude;
    private Float longitude;
    private String complement;
}
