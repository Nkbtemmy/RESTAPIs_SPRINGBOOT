package com.imanzi.springBootAPIs.controllers;

import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imanzi.springBootAPIs.models.Event;
import com.imanzi.springBootAPIs.models.Response;
import com.imanzi.springBootAPIs.services.EventServices;



@RestController
@RequestMapping(value="/api/v1/events")
public class EventsControllers {
	
	@Autowired
	private EventServices eventServices;
	
	//creating event
	@PostMapping(path="/create")
	public ResponseEntity<Response> createEvent(@RequestBody Event event) {
		return ResponseEntity.created(null).body(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.result(eventServices.createEvent(event))
						.message("Endpoint run  successfully")
						.status(HttpStatus.CREATED)
						.statusCode(HttpStatus.CREATED.value())
						.build()
				);
	}

	//Assigning organiser to event
	@ResponseBody
	@PostMapping(path="/assign")
	public ResponseEntity<Response> assigningUserToEvent(@Valid @RequestBody Long eventId, @Valid @RequestBody Long userId) {
		return ResponseEntity.created(null).body(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.result(eventServices.assignOrganiserToEvent(eventId, userId))
						.message("Endpoint run  successfully")
						.status(HttpStatus.CREATED)
						.statusCode(HttpStatus.CREATED.value())
						.build()
				);
	}
	
	//view only one event
	@GetMapping(path="/{id}")
	public ResponseEntity<Response> viewEvent(@PathVariable(value = "id") Long eventId) {
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.result(eventServices.viewEventById(eventId))
						.message("Endpoint run  successfully")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}
	
	//view All events
	@GetMapping(path="/")
	@ResponseBody
	public ResponseEntity<Response> viewAllEvents(){ 
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.data(eventServices.viewEvents())
						.message("Endpoint run  successfully")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}

	// view events created by organiser
	@GetMapping(value="/user/{id}")
	@ResponseBody
	public ResponseEntity<Response> viewAllEventsCreatedByOrganiser(@PathVariable(value = "id") Long userId){
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.data(eventServices.viewEventsOfOrganiser(userId))
						.message("Endpoint run  successfully")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
		
	}
	
	//view all event in stated date range
	@PostMapping("/range")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ResponseBody
	public ResponseEntity<Response> getEventsInThisRangeOfDates(@RequestBody Event event){
		
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.data(eventServices.viewEventsInRange(event.getStartDate(), event.getEndDate()))
						.message("Endpoint run  successfully")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
		
		
	}
	
	// close or Open event
	@PutMapping(path="/status/{id}")
	@ResponseBody
	public ResponseEntity<Response> changeEventStatus(@PathVariable(value = "id") Long eventId){
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.obj(eventServices.closeOrOpenEvent(eventId))
						.message("Endpoint run  successfully")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}
}