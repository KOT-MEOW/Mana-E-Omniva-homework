package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.Address;
import lv.venta.model.City;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.model.Person;
import lv.venta.model.Size;
import lv.venta.repo.IAbstractCustomerRepo;
import lv.venta.repo.IAddressRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.repo.IPersonRepo;

@SpringBootApplication
public class IaPs1ManaEOmnivaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IaPs1ManaEOmnivaApplication.class, args);
	}
/*
	@Bean
	public CommandLineRunner testDB(
			IAddressRepo addressRepo, 
			ICustomerAsPersonRepo customerAsPesonRepo, 
			ICustomerAsCompanyRepo customerAsCompanyRepo, 
			IDriverRepo driverRepo, 
			IParcelRepo parcelRepo, 
			IPersonRepo personRepo, 
			IAbstractCustomerRepo abstractCustomerRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String...args) throws Exception{
				
				//Person Model check
				Person person1 = new Person("Ilja","985412-99887","Avlass");
				Person person2 = new Person("Evgenij","876431-84324","Lskep");
				Person person3 = new Person("Anna","321456-35484","Klaite");
				personRepo.save(person1);
				personRepo.save(person2);
				personRepo.save(person3);

				// Address Model check
				Address address1 = new Address(City.Ventspils,12,"Klausa iela");
				Address address2 = new Address(City.Riga,32,"Mikola iela");
				Address address3 = new Address(City.Liepaja,2,"Milestiba iela");
				Address address4 = new Address(City.Ventspils,24,"Vijola iela");
				Address address5 = new Address(City.Riga,7,"Cestisa iela");
				Address address6 = new Address(City.Liepaja,1,"Loksa iela");
				addressRepo.save(address1);
				addressRepo.save(address2);
				addressRepo.save(address3);
				addressRepo.save(address4);
				addressRepo.save(address5);
				addressRepo.save(address6);
				
				// Driver Model check
				Driver driver1 = new Driver("Viktor", "123456-12345","Kirmer",(float)3.5,"AT456789");
				Driver driver2 = new Driver("Kristina", "124878-84122","Vaita",(float)1.5,"AT994456");
				Driver driver3 = new Driver("Alekss", "147789-32193","Liska",(float)7.5,"AT885522");
				Driver driver4 = new Driver("Kristap", "654575-35784","Ksispo",(float)2.0,"AT922543");
				Driver driver5 = new Driver("Mikola", "778855-33554","Klapse",(float)15.0,"AT221548");
				Driver driver6 = new Driver("Anna", "852147-85223","Ninasta",(float)1.0,"AT773545");
				driverRepo.save(driver1);
				driverRepo.save(driver2);
				driverRepo.save(driver3);
				driverRepo.save(driver4);
				driverRepo.save(driver5);
				driverRepo.save(driver6);
				
				// CustomerAsPerson Model check
				CustomerAsPerson customerAsPerson1 = new CustomerAsPerson("985412-99887", person1);
				CustomerAsPerson customerAsPerson2 = new CustomerAsPerson("876431-84324", person2);
				CustomerAsPerson customerAsPerson3 = new CustomerAsPerson("321456-35484", person3);
				customerAsPesonRepo.save(customerAsPerson1);
				customerAsPesonRepo.save(customerAsPerson2);
				customerAsPesonRepo.save(customerAsPerson3);
				
				// CustomerAsCompany Model check
				CustomerAsCompany customerAsCompany1 = new CustomerAsCompany("LV35489765541", "IK KTOT");
				CustomerAsCompany customerAsCompany2 = new CustomerAsCompany("LV90387265361", "SIA Biesnas");
				CustomerAsCompany customerAsCompany3 = new CustomerAsCompany("LV27817625363", "IK Msters");
				customerAsCompanyRepo.save(customerAsCompany1);
				customerAsCompanyRepo.save(customerAsCompany2);
				customerAsCompanyRepo.save(customerAsCompany3);
				
				//AbstractCustomer check
				AbstractCustomer abstCustomer1 = new AbstractCustomer("98453145", address1, null, customerAsPerson1);
				AbstractCustomer abstCustomer2 = new AbstractCustomer("77846124", address4, null, customerAsPerson2);
				AbstractCustomer abstCustomer3 = new AbstractCustomer("92145687", address3, null, customerAsPerson3);
				AbstractCustomer abstCustomer4 = new AbstractCustomer("32315478", address2, customerAsCompany1, null);
				AbstractCustomer abstCustomer5 = new AbstractCustomer("84562156", address5, customerAsCompany2, null);
				AbstractCustomer abstCustomer6 = new AbstractCustomer("45899721", address6, customerAsCompany3, null);
				try {
					abstractCustomerRepo.save(abstCustomer1);
					abstractCustomerRepo.save(abstCustomer2);
					abstractCustomerRepo.save(abstCustomer3);
					abstractCustomerRepo.save(abstCustomer4);
					abstractCustomerRepo.save(abstCustomer5);
					abstractCustomerRepo.save(abstCustomer6);
				} catch (DataIntegrityViolationException e) {
				    System.out.println("history already exist");
				}
				
				//Parcel check
				Parcel parcel1 = new Parcel(true, (float)150.55, Size.L, abstCustomer1, driver3);
				Parcel parcel2 = new Parcel(true, (float)70.00, Size.L, abstCustomer2, driver2);
				Parcel parcel3 = new Parcel(false, (float)17.45, Size.X, abstCustomer3, driver1);
				Parcel parcel4 = new Parcel(false, (float)7.45, Size.M, abstCustomer4, driver4);
				Parcel parcel5 = new Parcel(true, (float)12.99, Size.S, abstCustomer5, driver5);
				Parcel parcel6 = new Parcel(false, (float)80.00, Size.XL, abstCustomer6, driver6);
				Parcel parcel7 = new Parcel(true, (float)21.40, Size.XL, abstCustomer2, driver6);
				try {
					parcelRepo.save(parcel1);
					parcelRepo.save(parcel2);
					parcelRepo.save(parcel3);
					parcelRepo.save(parcel4);
					parcelRepo.save(parcel5);
					parcelRepo.save(parcel6);
					parcelRepo.save(parcel7);
				} catch (DataIntegrityViolationException e) {
				    System.out.println("history already exist");
				}
				
				
			}
			
		};
	
	}
*/		
}
