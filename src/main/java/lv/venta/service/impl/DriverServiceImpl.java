package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Driver;
import lv.venta.repo.IDriverRepo;
import lv.venta.service.IDriverCRUDService;

@Service
public class DriverServiceImpl implements IDriverCRUDService {

	@Autowired
	private IDriverRepo driverRepo;

	@Override
	public ArrayList<Driver> retrieveAll() throws Exception {
		if(driverRepo.count()==0) throw new Exception("Driver database is empty");
		return (ArrayList<Driver>) driverRepo.findAll();
	}

	@Override
	public Driver retrieveById(int id) throws Exception {
		if(id < 0) throw new Exception("Id must be positive");

		if(driverRepo.existsById(id)) {
			return driverRepo.findById(id).get();
		}else {
			throw new Exception("Driver with this id not existed");
		}
	}

	@Override
	public void deleteById(int Id) throws Exception {
		Driver deleteDriver = retrieveById(Id);
		driverRepo.delete(deleteDriver);
	}

	@Override
	public void create(Driver driver) throws Exception {
		Driver existedDriver = driverRepo.findByNameAndSurnameAndPersonCode(driver.getName(), driver.getSurname(), driver.getPersonCode());
		
		if(existedDriver != null) throw new Exception("Driver already exists");
		
		driverRepo.save(driver);
	}

	@Override
	public void update(int id, Driver driver) throws Exception {
		Driver updateDriverData = retrieveById(id);
		
		updateDriverData.setName(driver.getName());
		updateDriverData.setSurname(driver.getSurname());
		updateDriverData.setPersonCode(driver.getPersonCode());
		updateDriverData.setLicenseNo(driver.getLicenseNo());
		updateDriverData.setExperienceInYears(driver.getExperienceInYears());
		
		driverRepo.save(updateDriverData);
	}
	
}