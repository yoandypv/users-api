package com.sacavix.users.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sacavix.users.persistence.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
