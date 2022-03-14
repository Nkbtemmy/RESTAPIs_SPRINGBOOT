package com.imanzi.springBootAPIs.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	protected LocalDateTime timeStamp;
	protected int statusCode;
	protected HttpStatus status;
	protected String reason;
	protected String message;
	protected String developerMessage;
	protected List<?> data;
	protected HashMap<?,?> obj;
	protected Object result;
}
