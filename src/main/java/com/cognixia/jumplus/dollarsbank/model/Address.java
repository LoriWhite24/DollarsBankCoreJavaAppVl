package com.cognixia.jumplus.dollarsbank.model;

/**
 * The model for address.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class Address {
	private Integer addressId;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	/**
	 * Default constructor.
	 */
	public Address() {
		this(-1, "N/A", "N/A", "N/A", "N/A");
	}
	/**
	 * Overloaded constructor.
	 * @param addressId the address's id
	 * @param street the address's street
	 * @param city the address's city
	 * @param state the address's state
	 * @param zipcode the address's zipcode
	 */
	public Address(Integer addressId, String street, String city, String state, String zipcode) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	/**
	 * Retrieves the address's id.
	 * @return Integer - the address's id
	 */
	public Integer getAddressId() {
		return addressId;
	}
	/**
	 * Updates the address's id.
	 * @param addressId the address's id
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	/**
	 * Retrieves the address's street.
	 * @return String - the address's street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * Updates the address's street.
	 * @param street the address's street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Retrieves the address's city.
	 * @return String - the address's city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Updates the address's city.
	 * @param city the address's city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Retrieves the address's state.
	 * @return String - the address's state
	 */
	public String getState() {
		return state;
	}
	/**
	 * Updates the address's state.
	 * @param state the address's state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * Retrieves the address's zipcode.
	 * @return String - the address's zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * Updates the address's zipcode.
	 * @param zipcode the address's zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * Generates a string representation for this model.
	 * @return String - the string representation for this model
	 */
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipcode=" + zipcode + "]";
	}
}
