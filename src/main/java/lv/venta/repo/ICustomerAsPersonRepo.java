package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsPerson;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson,Integer> {
	
	CustomerAsPerson findByCustomerCode(String customerCode);

	boolean existsByPersonCode(String personCode);

}