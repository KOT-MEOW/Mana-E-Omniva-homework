package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.model.Driver;
import lv.venta.service.IDriverCRUDService;

//localhost:8080/driver

@Controller
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private IDriverCRUDService driverService;
	
	@GetMapping("/show/all") //localhost:8080/driver/show/all
	public String getAllDriver(Model model) {
		try {
			ArrayList<Driver> allDrivers = driverService.retrieveAll(); 
			model.addAttribute("mydata", allDrivers);
			model.addAttribute("msg", "All drivers");
			return "driver-all-page";
		}catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/show/all/{id}") //localhost:8080/driver/show/all/{id}
	public String getDriverById(@PathVariable("id") int id, Model model) {
		try {
			Driver selectedDriver = driverService.retrieveById(id);
			model.addAttribute("mydata",selectedDriver);
			model.addAttribute("msg", "Driver filtered by id");
			return "driver-one-page";
		} catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/remove/{id}") //localhost:8080/driver/remove/{id}
	public String getDriverDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			driverService.deleteById(id);
			ArrayList<Driver> allDrivers = driverService.retrieveAll(); 
			model.addAttribute("mydata", allDrivers);
			model.addAttribute("msg", "All drivers (no deleted driver)");
			return "driver-all-page";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/add") //localhost:8080/driver/add
	public String getDriverInsert(Model model) {
		model.addAttribute("driver", new Driver());
		return "driver-add-page";
	} 
	
	@PostMapping("/add") 
	public String postDriverInsert(@Valid Driver driver, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return "driver-add-page";
		} else {
			driverService.create(driver);
			return "redirect:/driver/show/all";
		}
	}
	
	@GetMapping("/update/{id}") //localhost:8080/driver/update/{id}
	public String getDriverUpdateById(@PathVariable("id") int id, Model model) {
		try {
			Driver driverForUpdate = driverService.retrieveById(id);
			model.addAttribute("driver",driverForUpdate);
			model.addAttribute("id", id);
			return "driver-update-page";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update/{id}")
	public String postDriverUpdateById(@PathVariable("id") int id, 
			@Valid Driver driver, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "driver-update-page";
		}else {
			try {
				driverService.update(id, driver);
				return "redirect:/driver/show/all/"+ id;
			} catch (Exception e) {
				model.addAttribute("mydata", e.getMessage());
				return "error-page";
			}
		}
	}
		
}