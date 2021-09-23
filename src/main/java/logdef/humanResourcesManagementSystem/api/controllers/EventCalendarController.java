package logdef.humanResourcesManagementSystem.api.controllers;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import logdef.humanResourcesManagementSystem.business.abstracts.EventCalendarService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.EventCalendar;

@RestController
@RequestMapping("/api/eventCalendar")
@CrossOrigin
public class EventCalendarController {
	
	private EventCalendarService eventCalendarService;

	@Autowired
	public EventCalendarController(EventCalendarService eventCalendarService) {
		super();
		this.eventCalendarService = eventCalendarService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody EventCalendar eventCalendar) {
		return ResponseEntity.ok(this.eventCalendarService.add(eventCalendar));
	}
	
	@GetMapping("/getall")
	public DataResult<List<EventCalendar>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.eventCalendarService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByEventCalendarId")
	public DataResult<EventCalendar> getByEventCalendarId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.eventCalendarService.getByEventCalendarId(eventCalendarId);
	}
	
	@PutMapping("/updateByEventCalendarName/{eventCalendarId}")
	public Result updateByEventCalendarName(@RequestParam(value = "eventCalendarId") int eventCalendarId,@RequestParam(value = "eventCalendarNameUpdate") String eventCalendarNameUpdate) {
		return this.eventCalendarService.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
	}
	
	@DeleteMapping("/deleteByEventCalendarId/{eventCalendarId}")
	public Result deleteByEventCalendarId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.eventCalendarService.deleteByEventCalendarId(eventCalendarId);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		
		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		
		return errors;
		
	}

}
