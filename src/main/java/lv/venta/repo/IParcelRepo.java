package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Integer> {

	ArrayList<Parcel> findByDriverIdd(int id);

	ArrayList<Parcel> findByAbstractCustomerIdac(int id);

	ArrayList<Parcel> findByPriceLessThan(float price);

	ArrayList<Parcel> findByAbstractCustomerAddressCity(City city);

	@Query(nativeQuery = true, value = "SELECT sum(price) FROM parcel WHERE idac=(?1);")
	float calculateIncomeCustomerById(int idac);

	@Query(nativeQuery = true, value = "SELECT count(idpa) from parcel where order_delivery like (?1);")
	int countOfDeliveryForToday(String sintax);
	
}