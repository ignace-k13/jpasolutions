package be.abis.exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(name="costreet")
	private String street;
	@Column(name="costrno")
	private String nr;
	@Column(name="cotownno")
	private String zipcode;
	@Column(name="cotown")
	private String town;
	@Column(name="cocountr")
	private String countryCode;

	public Address() {
	}

	public Address(String street, String nr, String zipcode, String town, String countryCode) {
		this.street = street;
		this.nr = nr;
		this.zipcode = zipcode;
		this.town = town;
		this.countryCode=countryCode;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	

}
