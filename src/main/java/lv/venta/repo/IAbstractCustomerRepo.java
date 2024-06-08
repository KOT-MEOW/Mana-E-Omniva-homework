package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;

public interface IAbstractCustomerRepo extends CrudRepository<AbstractCustomer, Integer> {

	AbstractCustomer findByCustomerAsCompany(CustomerAsCompany customerAsCompany);

	AbstractCustomer findByCustomerAsPerson(CustomerAsPerson customerAsPerson);

	boolean existsByCustomerAsCompanyCompanyRegNo(String companyRegNo);

}