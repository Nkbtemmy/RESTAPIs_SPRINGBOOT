package com.imanzi.springBootAPIs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imanzi.springBootAPIs.models.Organiser;

public interface OrganiserRepository  extends JpaRepository< Organiser , Long>{
	@Query(value = "select u from Organiser u where u.email = ?1")
	Organiser findByEmailAddress(String email);

	@Query(value = "select u from Organiser u where u.email = ?1 and u.password = ?2")
	Organiser login(String email, String password);

}
