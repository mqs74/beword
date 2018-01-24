package pl.mqs.beword.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import pl.mqs.beword.db.util.StringHelper;

@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = -5868864142419394144L;

	@Id
	private Long id;
	
	private Integer type; 
	private String street;
	private String homeNumber;
	
	@Column(nullable = true)
	private String flatNumber;
	private String city;
	private String postalCode;
	private String country;
	
	public Address() {}
	
	public Address(Integer type, String street, String homeNumber, String flatNumber, String city, String postalCode, String country) {
		this.type = type;
		this.street = street;
		this.homeNumber = homeNumber;
		this.flatNumber = flatNumber;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	public Long getId() {
		return id;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getHomeNumber() {
		return homeNumber;
	}
	
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	
	public String getFlatNumber() {
		return flatNumber;
	}
	
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString() {
		return new StringBuilder().append(StringHelper.CLASS_OPEN_BRACKET).
				append(this.getClass()).
				append(StringHelper.NESTED_POINTER).
				append("type=").
				append(type).
				append(", street=").
				append(street).
				append(", homeNumber=").
				append(homeNumber).
				append(", flatNumber=").
				append(flatNumber).
				append(", city=").
				append(city).
				append(", postalCode=").
				append(postalCode).
				append(", country=").
				append(country).
				append(StringHelper.CLASS_CLOSE_BRACKET).toString();
	}
}
