package lv.venta.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
@Table(name = "DriverTable")
@Entity
public class Driver {

	@Id
	@Column(name = "Idd")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idd;
	
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
	
	@Column(name = "Experience_in_years")
	@Min(1)
	private float experienceInYears;
	
	@Column(name = "License_no")
	@NotNull
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[A]{1}[T]{1}[0-9]{6}", message = "Error License No data")
	private String licenseNo;
	
	// connection to Parcel
	@OneToMany(mappedBy = "driver")
	@ToString.Exclude
	private Collection<Parcel> parcels;
	
	public Driver(String name, String personCode, String surname, float experienceInYears, String licenseNo) {
		setName(name);
		setPersonCode(personCode);
		setSurname(surname);
		setExperienceInYears(experienceInYears);
		setLicenseNo(licenseNo);
	}
	
}