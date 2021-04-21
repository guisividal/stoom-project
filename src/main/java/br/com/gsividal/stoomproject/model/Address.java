package br.com.gsividal.stoomproject.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Address {
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
