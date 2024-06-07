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
@Table(name = "AddressTable")
@Entity
public class Address {

	@Id
	@Column(name = "Ida")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int ida;
	
	@Column(name = "City")
	@NotNull
	private City city;
	
	@Column(name = "House_No")
	@NotNull
	@Min(1)
	private int houseNo;
	
	@Column(name = "Street_or_house_title")
	@NotNull
	@Size(min = 1, max = 80)
	String streetOrHouseTitle;
	
	// connection to AbstractCustomer
	@OneToMany(mappedBy = "address")
	@ToString.Exclude
	private Collection<AbstractCustomer> abstarctCustomer;
	
	public Address(City city, int houseNo, String streetOrHouseTitle) {
		setCity(city);
		setHouseNo(houseNo);
		setStreetOrHouseTitle(streetOrHouseTitle);
	}
	
}