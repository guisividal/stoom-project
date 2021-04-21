package br.com.gsividal.stoomproject.repository;

import br.com.gsividal.stoomproject.dto.AddressDTO;
import br.com.gsividal.stoomproject.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository <Address, Long> {
}
