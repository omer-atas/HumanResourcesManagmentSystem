package logdef.humanResourcesManagementSystem.api.controllers;

import java.util.Date;
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

import logdef.humanResourcesManagementSystem.business.abstracts.ReligiousHolidaysService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.ReligiousHoliday;

@RestController
@RequestMapping("/api/religiousHoliday")
@CrossOrigin
public class ReligiousHolidayController {
	
	private ReligiousHolidaysService religiousHolidaysService;

	@Autowired
	public ReligiousHolidayController(ReligiousHolidaysService religiousHolidaysService) {
		super();
		this.religiousHolidaysService = religiousHolidaysService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody  ReligiousHoliday religiousHoliday) {
		return ResponseEntity.ok(this.religiousHolidaysService.add(religiousHoliday));
	}
	
	@GetMapping("/getall")
	public DataResult<List<ReligiousHoliday>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.religiousHolidaysService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByEventCalendarId")
	public DataResult<ReligiousHoliday> getByEventCalendarId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.religiousHolidaysService.getByEventCalendarId(eventCalendarId);
	}
	
	@GetMapping("/getByReligiouslHolidayName")
	public DataResult<ReligiousHoliday> getByReligiouslHolidayName(@RequestParam(value = "religiouslHolidayName") String religiouslHolidayName) {
		return this.religiousHolidaysService.getByReligiouslHolidayName(religiouslHolidayName);
	}
	
	@PutMapping("/updateByEventCalendarName/{eventCalendarId}")
	public Result updateByEventCalendarName(@RequestParam(value = "eventCalendarId") int eventCalendarId,@RequestParam(value = "eventCalendarNameUpdate") String eventCalendarNameUpdate) {
		return this.religiousHolidaysService.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
	}
	
	@PutMapping("/updateByReligiouslHolidayName/{eventCalendarId}")
	public Result updateByReligiouslHolidayName(@RequestParam(value = "eventCalendarId") int eventCalendarId, @RequestParam(value = "religiouslHolidayNameUpdate") String religiouslHolidayNameUpdate) {
		return this.religiousHolidaysService.updateByReligiouslHolidayName(eventCalendarId, religiouslHolidayNameUpdate);
	}
	
	@PutMapping("/updateByStartingDate/{eventCalendarId}")
	public Result updateByStartingDate(int eventCalendarId,  @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date startingDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.religiousHolidaysService.updateByStartingDate(eventCalendarId, startingDateUpdate);
	}
	
	@PutMapping("/updateByEndDate/{eventCalendarId}")
	public Result updateByEndDate(int eventCalendarId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date endDateUpdate = new Date( day +"/"+ mounth +"/"+ year);		
		
		return this.religiousHolidaysService.updateByEndDate(eventCalendarId, endDateUpdate);
		
	}
	
	@DeleteMapping("/deleteByReligiousHolidayId/{eventCalendarId}")
	public Result deleteByReligiousHolidayId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.religiousHolidaysService.deleteByReligiousHolidayId(eventCalendarId);
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
