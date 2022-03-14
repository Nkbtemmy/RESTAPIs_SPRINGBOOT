package com.imanzi.springBootAPIs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imanzi.springBootAPIs.exceptions.ResourceNotFoundException;
import com.imanzi.springBootAPIs.models.Event;
import com.imanzi.springBootAPIs.models.Organiser;
import com.imanzi.springBootAPIs.repositories.EventRepository;
import com.imanzi.springBootAPIs.repositories.OrganiserRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EventServices {
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private OrganiserRepository organiserRepo;
	
	public Event createEvent(Event newEvent) {
		return eventRepo.save(newEvent);
	}
	
	public List<Event> viewEvents() {
		return eventRepo.findAll();
	}
	
	public Event viewEventById(Long id) throws ResourceNotFoundException {
		return eventRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with id :: " + id + " Is not Found"));
	}
	
	public List<Event> viewEventsInRange(Date initialDate, Date finalDate) {
		return eventRepo.findByDateRange(initialDate, finalDate);
	}
	
	public List<Event> viewEventsOfOrganiser(Long id) {
		Optional<Organiser> checkOrganiser = organiserRepo.findById(id);
		if(checkOrganiser.isPresent()) {
			return eventRepo.findEventsByOrgId(id);
		}
		return null;
		
	}
	 
	public Event assignOrganiserToEvent(Long eventId, Long organiserId) throws ResourceNotFoundException  {
		Event eventExist = eventRepo.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event with id :: " + eventId + " Is not Found"));
		Organiser organiserExist = organiserRepo.findById(organiserId).orElseThrow(() -> new ResourceNotFoundException("Organiser with id :: " + organiserId + " Is not Found"));
		eventExist.setOrgId(organiserExist.getId());
		return eventExist;
	}
	
	public HashMap<?, ?> closeOrOpenEvent(Long eventId) {
		Optional<Event> eventExist =  eventRepo.findById(eventId);
		HashMap<String, String> res = new HashMap<>();
		if(eventExist.isPresent()) {
			Event event = eventRepo.findById(eventId).orElseThrow(null);
			event.setOpen(event.getIsOpen()?false:true);
			eventRepo.save(event);
			res.put("message", "Event with id :: " + eventId + " Is now "+ event.getIsOpen());
			return res;
		}
		res.put("message", "Event with id :: " + eventId + " Is not exist in our system ");
		res.put("status", "true");
		return res;
	}
}
