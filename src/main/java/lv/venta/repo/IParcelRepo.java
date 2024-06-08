package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Integer> {

	ArrayList<Parcel> findByDriverIdd(int id);

	ArrayList<Parcel> findByAbstractCustomerIdac(int id);

	ArrayList<Parcel> findByPriceLessThan(float price);

	ArrayList<Parcel> findByAbstractCustomerAddressCity(City city);

	float calculateIncomeCustomerById(int id);

	int countOfDeliveryForToday(String deliverDate);

}