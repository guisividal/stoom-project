package br.com.gsividal.stoomproject.controller;

import br.com.gsividal.stoomproject.dto.AddressDTO;
import br.com.gsividal.stoomproject.model.Address;
import br.com.gsividal.stoomproject.repository.AddressRepository;
import br.com.gsividal.stoomproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public AddressDTO getAddress(@RequestBody AddressDTO addressDTO) {
        addressService.getAddress(convert(addressDTO));

        return addressDTO;
    }

    @PostMapping
    public AddressDTO createAddress(@RequestBody AddressDTO addressDTO) {
        addressService.createAddress(convert(addressDTO));

        return addressDTO;
    }

    @PutMapping
    public AddressDTO editAddress(@RequestBody AddressDTO addressDTO) {
        addressService.editAddress(convert(addressDTO));

        return addressDTO;
    }

    @DeleteMapping
    public AddressDTO deleteAddress(@RequestBody AddressDTO addressDTO) {
        addressService.deleteAddress(convert(addressDTO));

        return addressDTO;
    }

    private Address convert (AddressDTO addressDTO) {
        Address address = new Address();

        address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setNumber(addressDTO.getNumber());
        address.setCountry(addressDTO.getCountry());
        address.setZipcode(addressDTO.getZipcode());
        address.setLatitude(addressDTO.getLatitude());
        address.setLongitude(addressDTO.getLongitude());
        address.setComplement(addressDTO.getComplement());
        address.setStreetName(addressDTO.getStreetName());
        address.setNeighbourhood(addressDTO.getNeighbourhood());

        return address;
    }
}
