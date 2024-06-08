package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Driver;

public interface IDriverCRUDService {
	
	public abstract ArrayList<Driver> retrieveAll() throws Exception;
	
	public abstract Driver retrieveById(int id) throws Exception;
	
	public abstract void deleteById(int Id) throws Exception;
	
	public abstract void create(Driver driver) throws Exception;
	
	public abstract void update(int id, Driver driver) throws Exception;
	
}