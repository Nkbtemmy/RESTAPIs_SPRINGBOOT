package com.imanzi.springBootAPIs.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imanzi.springBootAPIs.exceptions.BadRequest;
import com.imanzi.springBootAPIs.exceptions.ResourceNotFoundException;
import com.imanzi.springBootAPIs.models.Organiser;
import com.imanzi.springBootAPIs.repositories.OrganiserRepository;

@Service
public class OrganiserServices {
	@Autowired
	private  OrganiserRepository organiserRepo ;
	
	 public Organiser saveOrganiser(Organiser user) throws ResourceNotFoundException {
		    Organiser savedUser = organiserRepo.findByEmailAddress(user.getEmail());
		    final String email = user.getEmail();
		    if (savedUser != null && email.equals(savedUser.getEmail())) {
		      throw new BadRequest("User with email:: " + email + " already exists");
		    }
		    return organiserRepo.save(user);
	 }

	public Organiser  loginOrganiser(String email, String password) throws ResourceNotFoundException {
		Organiser  foundUser = organiserRepo.login(email, password);
	    if (foundUser == null) {
	        throw new BadRequest("Invalid login");
	      }
	      return foundUser;
	}
	
	public Organiser  updateOrganiser(Organiser organiser) {
		return organiserRepo.save(organiser);
	}
	
	public List<Organiser> listOfAllOrganiser() {
		return organiserRepo.findAll();
	}
	public Map<?, ?>  deleteOrganiser(Long id)  throws ResourceNotFoundException {
		Optional<Organiser> isUserExist = organiserRepo.findById(id);
		HashMap<String, String> response = new HashMap<>();
		if(isUserExist.isPresent()) {
			Organiser currentUser = organiserRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organiser with id :: " + id + " Is not Found"));
			currentUser.setDeleted(true);
			organiserRepo.save(currentUser);
			  response.put("deleted", "True");
			    response.put("status", "success");
			    return response;
		}
		response.put("message", "organiser with this id ::"+id+" is not in system");
		response.put("status", "false");
		return response;
	}
}
