package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "PersonTable")
@Entity
public class Person {
	
	@Id
	@Column(name = "Idp")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idp;
	
	@Column(name = "Name")
	@NotNull
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z]+", message = "You can use only letters and space")
	private String name;
	
	@Column(name = "Person_code")
	@NotNull
	@Size(min = 12, max = 12)
	@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "You can use only numbers and -")
	private String personCode;
	
	@Column(name = "Surname")
	@NotNull
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z]+", message = "You can use only letters and space")
	private String surname;
	
	// connection to CustomerAsPerson
	@OneToOne(mappedBy = "person")
	@ToString.Exclude
	private CustomerAsPerson customerAsPerson;
	
	public Person(String name, String surname, String personCode) {
		setName(name);
		setSurname(surname);
		setPersonCode(personCode);
	}
	
}