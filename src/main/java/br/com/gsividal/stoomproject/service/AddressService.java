package br.com.gsividal.stoomproject.service;

import br.com.gsividal.stoomproject.model.Address;
import br.com.gsividal.stoomproject.repository.AddressRepository;
import br.com.gsividal.stoomproject.service.geocoding.Coordinates;
import br.com.gsividal.stoomproject.service.geocoding.GeocodingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GeocodingService geocodingService;

    public Address createAddress(Address address) {
        log.info("Creating address with id: {}", address.getId());

        if (addressRepository.findById(address.getId()).isPresent()) {
            throw new IllegalArgumentException("Address already created for Id: " + address.getId());
        }

        if (address.getLatitude() == null || address.getLongitude() == null) {
            Coordinates coordinates = geocodingService.getCoordinates(
                    address.getStreetName(),
                    address.getNumber(),
                    address.getNeighbourhood(),
                    address.getCity(),
                    address.getState(),
                    address.getCountry());
            address.setLatitude(coordinates.getLatitude());
            address.setLongitude(coordinates.getLongitude());
        }

        return addressRepository.save(address);
    }

    public Optional<Address> getAddress(Long id) {
        log.info("Searching address with id: {}", id);

        return addressRepository.findById(id);
    }

    public Optional<Address> deleteAddress(Long id) {
        log.info("Deleting address with id: {}", id);
        return addressRepository.findById(id)
                .map(addressFound -> {
                    addressRepository.deleteById(id);
                    return addressFound;
                });
    }

    public Optional<Address> editAddress(Address addressUpdated) {
        log.info("Editing address with id: {}", addressUpdated.getId());

        return addressRepository.findById(addressUpdated.getId())
                .map(address -> {
                    address.setStreetName(addressUpdated.getStreetName());
                    address.setNumber(addressUpdated.getNumber());
                    address.setNeighbourhood(addressUpdated.getNeighbourhood());
                    address.setCity(addressUpdated.getCity());
                    address.setState(addressUpdated.getState());
                    address.setCountry(addressUpdated.getCountry());
                    address.setZipcode(addressUpdated.getZipcode());

                    address.setComplement(addressUpdated.getComplement());

                    if (addressUpdated.getLatitude() == null || addressUpdated.getLongitude() == null) {
                        Coordinates coordinates = geocodingService.getCoordinates(
                                addressUpdated.getStreetName(),
                                addressUpdated.getNumber(),
                                addressUpdated.getNeighbourhood(),
                                addressUpdated.getCity(),
                                addressUpdated.getState(),
                                addressUpdated.getCountry());
                        address.setLatitude(coordinates.getLatitude());
                        address.setLongitude(coordinates.getLongitude());
                    } else {
                        address.setLatitude(addressUpdated.getLatitude());
                        address.setLongitude(addressUpdated.getLongitude());
                    }
                    return address;
                })
                .map(addressRepository::save);
    }
}