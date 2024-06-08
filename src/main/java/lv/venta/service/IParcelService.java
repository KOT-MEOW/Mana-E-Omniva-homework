package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelService {

	public abstract ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception;
	
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception;
	
	public abstract ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception;
	
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception;
	
	public abstract void insertNewParcelByCustomerCodeAndDriverId(String customer_code, int id, Parcel parcel) throws Exception;
	
	public abstract void changeParcelDriverByParcelIdAndDriverId(int idp, int idd) throws Exception;
	 
	public abstract float calculateIncomeOfParcelsByCustomerId(int id) throws Exception;
	
	public abstract int calculateHowManyParcelsNeedToDeliverToday() throws Exception;
	
}