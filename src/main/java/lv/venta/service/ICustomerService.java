package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;

public interface ICustomerService {

public abstract void insertNewCustomerAsPerson(CustomerAsPerson customerAsPerson) throws Exception;

public abstract void insertNewCustomerAsCompany(CustomerAsCompany customerAsCompany) throws Exception;
  
public abstract void addAddressToCustomerByCustomerId(int id, AbstractCustomer abstractCustomer) throws Exception;

AbstractCustomer retrieveById(int id) throws Exception;

ArrayList<AbstractCustomer> retrieveAll() throws Exception;

}