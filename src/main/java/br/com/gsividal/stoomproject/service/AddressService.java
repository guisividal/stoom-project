package br.com.gsividal.stoomproject.service;

import br.com.gsividal.stoomproject.model.Address;
import br.com.gsividal.stoomproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        System.out.println("Salvando no banco!!!");

        if(address.getLatitude().isNaN() || address.getLongitude().isNaN()) {
            // método do google para pegar lat + long
        }

        addressRepository.save(address);

        return address;
    }

    public Address getAddress(Address address) {
        System.out.println("Salvando no banco!!!");

        addressRepository.findById(String.valueOf(address.getId()));

        return address;
    }

    public Address deleteAddress(Address address) {
        System.out.println("Salvando no banco!!!");

        addressRepository.delete(address);

        return address;
    }

    public Address editAddress(Address address) {
        System.out.println("Salvando no banco!!!");

        if(address.getLatitude().isNaN() || address.getLongitude().isNaN()) {
            // método do google para pegar lat + long
        }

        // qual metodo update?
        addressRepository.save(address);

        return address;
    }
}
