package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.Address;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.repo.IAbstractCustomerRepo;
import lv.venta.repo.IAddressRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private IAbstractCustomerRepo abstractCustomerRepo;
	
	@Autowired
	private ICustomerAsPersonRepo customerAsPersonRepo;
	
	@Autowired
	private ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	@Autowired
	private IAddressRepo addressRepo;
	

	@Override
	public void insertNewCustomerAsPerson(CustomerAsPerson customerAsPerson) throws Exception {
		if(customerAsPersonRepo.existsByPersonCode(customerAsPerson.getPersonCode())) throw new Exception("Customer with person code exists");
		
		CustomerAsPerson newPerson = new CustomerAsPerson(customerAsPerson.getPersonCode(), customerAsPerson.getPerson());
		customerAsPersonRepo.save(newPerson);
	}

	@Override
	public void insertNewCustomerAsCompany(CustomerAsCompany customerAsCompany) throws Exception {
		if(abstractCustomerRepo.existsByCustomerAsCompanyCompanyRegNo(customerAsCompany.getCompanyRegNo())) throw new Exception("Customer with company reg. number exists");
		
		CustomerAsCompany newCompnay = new CustomerAsCompany(customerAsCompany.getCompanyRegNo(), customerAsCompany.getTitle());
		customerAsCompanyRepo.save(newCompnay);
	}

	@Override
	public void addAddressToCustomerByCustomerId(int id, AbstractCustomer abstractCustomer) throws Exception {
		AbstractCustomer customerToAddAddress = retrieveById(id);

		Address newAddress = abstractCustomer.getAddress();
		
		if(newAddress.getIda()==0) {
			addressRepo.save(newAddress);
		}
		
		customerToAddAddress.setAddress(abstractCustomer.getAddress());
		abstractCustomerRepo.save(customerToAddAddress);
	}
	
	@Override
	public AbstractCustomer retrieveById(int id) throws Exception {
		if(id < 0) throw new Exception("Id should be positive");
		
		if(abstractCustomerRepo.existsById(id)) {
			return abstractCustomerRepo.findById(id).get();
		}else {
			throw new Exception("Driver with id not exist");
		}
	}
	
	@Override
	public ArrayList<AbstractCustomer> retrieveAll() throws Exception {
		if(abstractCustomerRepo.count()==0) throw new Exception("Database is empty");
		
		return (ArrayList<AbstractCustomer>) abstractCustomerRepo.findAll();
	}
	

}