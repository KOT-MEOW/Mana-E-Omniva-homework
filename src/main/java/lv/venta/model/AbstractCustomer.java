package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "AbstractCustomerTable")
@Entity
public class AbstractCustomer {

	@Id
	@Column(name = "Idac")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idac;
	
	@Column(name = "Phone_no")
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[0-9]{8}", message = "Only numbers are allowed")
	private String phoneNo;
	
	// connection to Address
	@ManyToOne
	@JoinColumn(name="Ida")
	private Address address;
	
	// connection to CustomerAsCompany
	@OneToOne
	@JoinColumn(name="Idcc")
	private CustomerAsCompany customerAsCompany;
	
	// connection to CustomerAsPerson
	@OneToOne
	@JoinColumn(name="Idcp")
	private CustomerAsPerson customerAsPerson;
	
	public AbstractCustomer(String phoneNo, Address address, CustomerAsCompany customerAsCompany, CustomerAsPerson customerAsPerson) {
		setPhoneNo(phoneNo);
		setAddress(address);
		setCustomerAsCompany(customerAsCompany);
		setCustomerAsPerson(customerAsPerson);
	}
}