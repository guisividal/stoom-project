package br.com.gsividal.stoomproject.controller;

import br.com.gsividal.stoomproject.dto.AddressDTO;
import br.com.gsividal.stoomproject.model.Address;
import br.com.gsividal.stoomproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // CREATE
    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        Address address = addressService.createAddress(convert(addressDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(convert(address));
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> readAddress(@PathVariable("id") Long id) {
        return addressService.readAddress(id)
                .map(address -> ResponseEntity.status(HttpStatus.OK).body(convert(address)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // UPDATE
    @PutMapping
    public ResponseEntity<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO addressDTO) {
        return addressService.updateAddress(convert(addressDTO))
                .map(address -> ResponseEntity.status(HttpStatus.OK).body(convert(address)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDTO> deleteAddress(@PathVariable(value = "id") Long id) {
        return addressService.deleteAddress(id)
                .map(address -> ResponseEntity.status(HttpStatus.OK).body(convert(address)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private AddressDTO convert(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setLatitude(address.getLatitude());
        addressDTO.setLongitude(address.getLongitude());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setStreetName(address.getStreetName());
        addressDTO.setNeighbourhood(address.getNeighbourhood());

        return addressDTO;
    }

    private Address convert(AddressDTO addressDTO) {
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