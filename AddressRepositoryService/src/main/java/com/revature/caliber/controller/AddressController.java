package com.revature.caliber.controller;

import java.util.List;

<<<<<<< HEAD
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.model.Address;
import com.revature.caliber.repository.AddressRepository;


@RestController
public class AddressController {
	
	private static final Logger log = Logger.getLogger(AddressController.class);
	
	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * Create location
	 *
	 * @param location
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/vp/location/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Address> createLocation(@Valid @RequestBody Address location) {
		log.info("Saving location: " + location);
		addressRepository.save(location);
		return new ResponseEntity<>(location, HttpStatus.CREATED);
	}

	/**
	 * Update location
	 *
	 * @param location
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/vp/location/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> updateLocation(@Valid @RequestBody Address location) {
		log.info("Updating location: " + location);
		addressRepository.save(location);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Returns all locations from the database `
	 *
	 * @return
	 */
	@RequestMapping(value = "/all/location/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING', 'PANEL')")
	public ResponseEntity<List<Address>> getAllLocations() {
		log.info("Fetching all locations");
		List<Address> locations = addressRepository.findAll();
		return new ResponseEntity<>(locations, HttpStatus.OK);
	}

	/**
	 * Removes the location
	 *
	 * @param location
	 * @return response entity
	 */
	@RequestMapping(value = "/vp/location/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> removeLocation(@Valid @RequestBody Address location) {
		log.info("Deactivating location: " + location);
		addressRepository.delete(location);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Reactivates the location
	 *
	 * @param location
	 * @return response entity
	 */
	@RequestMapping(value = "/vp/location/reactivate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> reactivateLocation(@Valid @RequestBody Address location) {
		log.info("Updating location: " + location);
		addressRepository.save(location);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.model.Address;
import com.revature.caliber.repository.AddressRepository;

/**
 * Used for assessment CRUD operations. Includes both Trainer and QC assessments
 * 
 * @author Emmanuel George
 *
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/AddressController")
public class AddressController {

	@Autowired
	AddressRepository adao;

	/**
	 * User gets all address objects from table
	 *
	 * @param addressId
	 * @return addressList
	 */
	@GetMapping(value = "/getAddresses", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Address> get_addresses() {
		List<Address> addressList = adao.findAll();
		return addressList;
	}

	/**
	 * User provides id# address table is searched, return object if found
	 *
	 * @param addressId
	 * @return address object
	 */
	@GetMapping(value = "/getAddress", produces = MediaType.APPLICATION_JSON_VALUE)
	public Address get_address(@RequestParam(value = "addressId", required = true) int addressId) {
		Address address = adao.findOne(addressId);
		return address;
	}

	/**
	 * User provides all values for an address except for id#, new address is set to
	 * values provided and saved to table
	 *
	 * @param street,
	 *            city, state, zipcode, company, active
	 */
	@GetMapping(value = "/addAddress", produces = MediaType.APPLICATION_JSON_VALUE)
	public void add_address(@RequestParam(value = "street", required = true) String street,
			@RequestParam(value = "city", required = true) String city,
			@RequestParam(value = "state", required = true) String state,
			@RequestParam(value = "zipcode", required = true) String zipcode,
			@RequestParam(value = "company", required = true) String company,
			@RequestParam(value = "active", required = true) boolean active) {

		Address address = new Address();

		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZipcode(zipcode);
		address.setCompany(company);
		address.setActive(active);

		adao.save(address);
	}

	/**
	 * User provides values, address is set to values provided and saved to table
	 *
	 * @param street,
	 *            city, state, zipcode, company, active
	 */
	@GetMapping(value = "/updateAddress", produces = MediaType.APPLICATION_JSON_VALUE)
	public void update_address(@RequestParam(value = "addressId", required = true) int addressId,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zipcode", required = false) String zipcode,
			@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "active", required = false) Boolean active) {

		Address address = adao.findOne(addressId);

		if (street != null)
			address.setStreet(street);
		if (city != null)
			address.setCity(city);
		if (state != null)
			address.setState(state);
		if (zipcode != null)
			address.setZipcode(zipcode);
		if (company != null)
			address.setCompany(company);
		if (active != null)
			address.setActive(active);

		adao.save(address);
	}

	/**
	 * User provides id# address table is searched, delete address object if found
	 *
	 * @param addressId
	 */
	@GetMapping(value = "/deleteAddress", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete_address(@RequestParam(value = "addressId", required = true) int addressId) {

		Address address = adao.findOne(addressId);
		adao.delete(address);
>>>>>>> 589f7b7a641ca446d4b17e62b9a18cf492f2c556
	}

}
