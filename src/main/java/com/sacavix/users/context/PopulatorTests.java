package com.sacavix.users.context;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.sacavix.users.persistence.entity.Address;
import com.sacavix.users.persistence.entity.Person;
import com.sacavix.users.persistence.repository.AddressRepository;
import com.sacavix.users.persistence.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PopulatorTests implements InitializingBean {

	private AddressRepository addressRepository;
	private PersonRepository personRepository;

	public PopulatorTests(AddressRepository addressRepository, PersonRepository personRepository) {
		super();
		this.addressRepository = addressRepository;
		this.personRepository = personRepository;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info(" === Vamos a popular la base de datos");
		populate();
		log.info("=== Fin");
	}

	public void populate() {

		for (int i = 1; i <= 10; i++) {
			Address addressSaved = this.addressRepository.save(getRandomAddress(i));
			Person tempPerson = getRandomPerson(i);
			tempPerson.setAddressId(addressSaved.getId());
			this.personRepository.save(tempPerson);
		}

	}

	private Address getRandomAddress(int i) {

		Address address = new Address();
		address.setCity("City " + i);
		address.setCountry("Country " + i);
		address.setPostalCode(i);
		address.setState("State " + i);
		address.setStreet("Street " + i);
		return address;
	}

	private Person getRandomPerson(int i) {
		Person p = new Person();
		p.setName("Pepito " + i);
		p.setEmail("pepito" + i + "@gmail.com");
		p.setPhoneNumber("45785125" + i);
		return p;
	}

}
