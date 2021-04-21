package br.com.gsividal.stoomproject.service;

import br.com.gsividal.stoomproject.model.Address;
import br.com.gsividal.stoomproject.repository.AddressRepository;
import br.com.gsividal.stoomproject.service.geocoding.Coordinates;
import br.com.gsividal.stoomproject.service.geocoding.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GeocodingService geocodingService;

    public Address createAddress(Address address) {
        System.out.println("Salvando no banco!!!");

        if (address.getLatitude() == null || address.getLongitude() == null) {
            // método do google para pegar lat + long
        }

        return addressRepository.save(address);
    }

    public Optional<Address> getAddress(Long id) {
        System.out.println("Buscando no banco!!!");

        return addressRepository.findById(id);
    }

    public Optional<Address> deleteAddress(Long id) {
        System.out.println("Deletando no banco!!!");
        return addressRepository.findById(id)
                .map(addressFound -> {
                    addressRepository.deleteById(id);
                    return addressFound;
                });
    }

    public Optional<Address> editAddress(Address addressUpdated) {
        System.out.println("Editando no banco!!!");
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
                                addressUpdated.getCity(),
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