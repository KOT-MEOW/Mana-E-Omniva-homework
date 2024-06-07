package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "CustomerAsPersonTable")
@Entity
public class CustomerAsPerson {

	@Id
	@Column(name = "Idcp")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idcp;
	
	@Column(name = "Customer_code")
	@Setter
	private String customerCode;
	
	@Column(name = "Person_code")
	@NotNull
	@Size(min = 12, max = 12)
	@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "You can use only numbers and -")
	private String personCode;
	
	// connection to Person
	@OneToOne
	@JoinColumn(name = "Idp")
	private Person person;
	
	// connection to AbstractCustomer
	@OneToOne(mappedBy = "customerAsPerson")
	@ToString.Exclude
	private AbstractCustomer abstractCustomer;
	
	public CustomerAsPerson(String personCode, Person person) {
		setCustomerCode(customerCode="0_person_"+personCode);
		setPersonCode(personCode);
		setPerson(person);
	}
	
}