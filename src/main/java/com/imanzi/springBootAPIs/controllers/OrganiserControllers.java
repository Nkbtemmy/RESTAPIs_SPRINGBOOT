package com.imanzi.springBootAPIs.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imanzi.springBootAPIs.models.Organiser;
import com.imanzi.springBootAPIs.models.Response;
import com.imanzi.springBootAPIs.repositories.OrganiserRepository;
import com.imanzi.springBootAPIs.services.OrganiserServices;


@RestController
@RequestMapping("/api/v1/organisers")
public class OrganiserControllers {
	@Autowired
	private OrganiserRepository organiserRepository;
	@Autowired
	private OrganiserServices services;
	//create Organiser
	@PostMapping(path = "/create")
	@ResponseBody
	public ResponseEntity<Response> createUser ( @Valid @RequestBody Organiser user) {
		return ResponseEntity.created(null).body(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.result(services.saveOrganiser(user))
						.message("Endpoint run  successfully")
						.status(HttpStatus.CREATED)
						.statusCode(HttpStatus.CREATED.value())
						.build()
				);
		   //return ResponseEntity.created(null).body(services.saveOrganiser(user));
	}
	
	//login organiser
	@PostMapping(path="/login")
	@ResponseBody
	public ResponseEntity<Response> LoginUser (@Valid @RequestBody Organiser user) {
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.result(services.loginOrganiser(user.getEmail(),user.getPassword()))
						.message("Endpoint run  successfully")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}
	
	//Delete organiser
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Response> deleteOrganiser(@PathVariable(value = "id") Long userId){
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.result(services.deleteOrganiser(userId))
						.message("Endpoint run  successfully")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}
	
	//Update organiser
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Response> updateUser(@RequestBody Organiser user, @PathVariable(value = "id") Long userId) {
		Optional<Organiser> findUser = organiserRepository.findById(userId);
		if(findUser.isPresent()) {
			return ResponseEntity.ok(
					Response.builder()
							.timeStamp(LocalDateTime.now())
							.result(services.updateOrganiser(user))
							.message("Endpoint run  successfully")
							.status(HttpStatus.OK)
							.statusCode(HttpStatus.OK.value())
							.build()
					);
			//return ResponseEntity.ok().body(services.updateOrganiser(user));
		}
		//return ResponseEntity.status(400).body(user);
		return ResponseEntity.status(400).body(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.result(user)
						.message("Endpoint run  successfully")
						.status(HttpStatus.FORBIDDEN)
						.statusCode(HttpStatus.FORBIDDEN.value())
						.build()
				);
	}
	
	//list all organisers
	@GetMapping("/")
	public ResponseEntity<List<Organiser>> listAllUsers(){
		return ResponseEntity.ok().body(services.listOfAllOrganiser());
	}
	
}
