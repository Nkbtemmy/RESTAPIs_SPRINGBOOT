package com.imanzi.springBootAPIs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imanzi.springBootAPIs.models.Response;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;



@RestController
public class DefaultController {
	@GetMapping(path = "/")
	public ResponseEntity<Response> message() {
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.message("Welcome to Nkubito's backend")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}

}