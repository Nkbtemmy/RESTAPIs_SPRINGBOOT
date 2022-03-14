package com.imanzi.springBootAPIs.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private  boolean isOpen = true;
	@Column
//	@NotBlank(message = "title is required")
	private  String title;
	@Column
//	@NotBlank(message = "event starting date is required")
	@JsonFormat(pattern="yyyy/MM/dd")
	@DateTimeFormat(iso = ISO.DATE)
	private  Date startDate = new Date();
	@Column
//	@NotBlank(message = "event starting time is required")
	private  String startTime;
	@Column
//	@NotBlank(message = "event Ending Date is required")
	@JsonFormat(pattern="yyyy/MM/dd")
	@DateTimeFormat(iso = ISO.DATE)
	private  Date endDate =  new Date();
	@Column
//	@NotBlank(message = "event ending time is required")
	private  String endTime;
	@Column
//	@NotBlank(message = "event location is required")
	private  String location;
	@Column
	//@NotBlank(message = "organiser Id is required")
	private  long orgId;
	@Column
	private  boolean isDeleted = false;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean getIsOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}

