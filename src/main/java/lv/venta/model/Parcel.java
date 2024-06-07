package lv.venta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Parcel")
@Entity
public class Parcel {

	
	@Id
	@Column(name = "Idpa")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idpa;
	
	@Column(name = "Is_fragile")
	@NotNull
	@Setter
	private boolean isFragile;
	
	@Column(name = "Order_created")
	@Setter
	private LocalDateTime orderCreated;
	
	@Column(name = "Order_delivery")
	@Setter
	private LocalDateTime orderDelivery;
	
	@Column(name = "Price")
	@NotNull
	@Setter
	@Min((long)0.1)
	private float price;
	
	@Column(name = "Size")
	@NotNull
	private Size size;
	
	// connection to AbstractCustomer
	@ManyToOne
	@JoinColumn(name = "Idac")
	private AbstractCustomer abstractCustomer;
	
	// connection to Driver
	@ManyToOne
	@JoinColumn(name = "Idd")
	private Driver driver;
	
	public Parcel(boolean is_fragile, float price, Size size, AbstractCustomer abstractCustomer, Driver driver) {
		setFragile(is_fragile);
		setOrderCreated(LocalDateTime.now());
		setOrderDelivery(LocalDateTime.now().plusDays(1));
		setPrice(price);
		setSize(size);
		setAbstractCustomer(abstractCustomer);
		setDriver(driver);
	}
	
}