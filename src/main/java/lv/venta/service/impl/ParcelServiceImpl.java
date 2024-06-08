package lv.venta.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.City;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Parcel;
import lv.venta.repo.IAbstractCustomerRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.service.IParcelService;

@Service
public class ParcelServiceImpl implements IParcelService{

	@Autowired
	private IParcelRepo parcelRepo;
	
	@Autowired
	private ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	@Autowired
	private ICustomerAsPersonRepo customerAsPersonRepo;
	
	@Autowired
	private IDriverRepo driverRepo;
	
	@Autowired
	private IAbstractCustomerRepo abstractCustomerRepo;
	
	
	@Override
	public ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception {
		if(id < 0) throw new Exception("Id must be positive");
		
		if(!abstractCustomerRepo.existsById(id)) throw new Exception("No customer with your id");
		
		ArrayList<Parcel> result = parcelRepo.findByAbstractCustomerIdac(id);
		
		if(result.isEmpty()) throw new Exception("No parcel with your id (customer)");
		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception {
		if(id < 0) throw new Exception("Id must be positive");
		
		ArrayList<Parcel> result = parcelRepo.findByDriverIdd(id);
		
		if(result.isEmpty()) throw new Exception("No parcel with your id (driver)");
		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception {
		if(price < 0.0) throw new Exception("Price must be positive");
		
		ArrayList<Parcel> result = parcelRepo.findByPriceLessThan(price);
		
		if(result.isEmpty()) throw new Exception("No parcel with price less than " + price);
		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception {
		ArrayList<Parcel> result = parcelRepo.findByAbstractCustomerAddressCity(city);
		
		if(result.isEmpty()) throw new Exception("No order to your city");
		return result;
	}

	@Override
	public void insertNewParcelByCustomerCodeAndDriverId(String customerCode, int id, Parcel parcel) throws Exception {
		if(customerAsCompanyRepo.findByCustomerCode(customerCode)==null && customerAsPersonRepo.findByCustomerCode(customerCode)==null) throw new  Exception("Customer with customer code not exists");
		
		if(driverRepo.findById(id)==null) throw new Exception("No driver with id");
		
		Parcel newParcel = parcel; 

		CustomerAsCompany customerAsCompany = customerAsCompanyRepo.findByCustomerCode(customerCode);
		CustomerAsPerson customerAsPerson = customerAsPersonRepo.findByCustomerCode(customerCode);
		
		if (customerAsCompany != null) {
			AbstractCustomer newCustomer = abstractCustomerRepo.findByCustomerAsCompany(customerAsCompany);
			newParcel.setAbstractCustomer(newCustomer);
			newParcel.setDriver(driverRepo.findByIdd(id));
			
			parcelRepo.save(newParcel);
			
		} else if (customerAsPerson != null) {
			AbstractCustomer newCustomer = abstractCustomerRepo.findByCustomerAsPerson(customerAsPerson);
			newParcel.setAbstractCustomer(newCustomer);
			newParcel.setDriver(driverRepo.findByIdd(id));
			
			parcelRepo.save(newParcel);
		}
		
	}

	@Override
	public void changeParcelDriverByParcelIdAndDriverId(int idp, int idd) throws Exception {
		if(idp < 0) throw new Exception("Id must be positive (Parcel)");
		
		Parcel parcelForUpdating = parcelRepo.findById(idp).get();
		if(parcelForUpdating.equals(null)) throw new Exception("Parcel with id not exists");
		
		if(idd < 0) throw new Exception("Id must be positive (Driver)");
		if(driverRepo.findByIdd(idd).equals(null)) throw new Exception("Driver with id not exists");

		parcelForUpdating.setDriver(driverRepo.findByIdd(idd));
		parcelRepo.save(parcelForUpdating);
	}

	@Override
	public float calculateIncomeOfParcelsByCustomerId(int id) throws Exception {
		if(id < 1) throw new Exception("Id should be positive");
		
		if(!abstractCustomerRepo.existsById(id)) throw new Exception("Customer with id not exists");
		
		float result = parcelRepo.calculateIncomeCustomerById(id);
		
		if(result == 0) throw new Exception("No parcels");
		
		return result;
	}

	@Override
	public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {

		int todayYear = LocalDateTime.now().getYear();
		int todayMonth = LocalDateTime.now().getMonthValue();
		int todayDay = LocalDateTime.now().getDayOfMonth();
		
		String deliverDate = "'" +todayYear + "-" + todayMonth + "-" + todayDay + "'%";
		
		int result = parcelRepo.countOfDeliveryForToday(deliverDate);

		return result;
	}

}