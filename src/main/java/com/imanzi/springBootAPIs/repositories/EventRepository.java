package com.imanzi.springBootAPIs.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;

import com.imanzi.springBootAPIs.models.Event;


public interface EventRepository extends JpaRepository< Event , Long> {
	@Query("SELECT e FROM Event e WHERE startDate >= :startDate AND endDate <= :endDate")
	List<Event> findByDateRange(@Temporal(TemporalType.DATE) Date startDate,@Temporal(TemporalType.DATE) Date endDate);
	
	@Query("SELECT e FROM Event e WHERE orgId = ?1 ORDER BY startDate ASC")
	List<Event> findEventsByOrgId(Long id);
}
