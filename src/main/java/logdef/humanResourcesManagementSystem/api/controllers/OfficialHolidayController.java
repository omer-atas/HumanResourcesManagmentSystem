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

import logdef.humanResourcesManagementSystem.business.abstracts.OfficialHolidayService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.EventCalendar;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialHoliday;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialPermission;

@RestController
@RequestMapping("/api/officialHoliday")
@CrossOrigin
public class OfficialHolidayController {
	
	private OfficialHolidayService officialHolidayService;

	@Autowired
	public OfficialHolidayController(OfficialHolidayService officialHolidayService) {
		super();
		this.officialHolidayService = officialHolidayService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody OfficialHoliday OfficialHoliday) {
		return ResponseEntity.ok(this.officialHolidayService.add(OfficialHoliday));
	}
	
	@GetMapping("/getall")
	public DataResult<List<OfficialHoliday>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.officialHolidayService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByEventCalendarId")
	public DataResult<OfficialHoliday> getByEventCalendarId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.officialHolidayService.getByEventCalendarId(eventCalendarId);
	}
	
	@GetMapping("/getByOfficialHolidayName")
	public DataResult<OfficialHoliday> getByOfficialHolidayName(@RequestParam(value = "officialHolidayName") String officialHolidayName) {
		return this.officialHolidayService.getByOfficialHolidayName(officialHolidayName);
	}
	
	@PutMapping("/updateByEventCalendarName/{eventCalendarId}")
	public Result updateByEventCalendarName(@RequestParam(value = "eventCalendarId") int eventCalendarId,@RequestParam(value = "eventCalendarNameUpdate")  String eventCalendarNameUpdate) {
		return this.officialHolidayService.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
	}
	
	@PutMapping("/updateByOfficialHolidayName/{eventCalendarId}")
	public Result updateByOfficialHolidayName(@RequestParam(value = "eventCalendarId") int eventCalendarId,@RequestParam(value = "officialHolidayNameUpdate") String officialHolidayNameUpdate) {
		return this.officialHolidayService.updateByOfficialHolidayName(eventCalendarId, officialHolidayNameUpdate);
	}
	
	@PutMapping("/updateByStartingDate/{eventCalendarId}")
	public Result updateByStartingDate(@RequestParam(value = "eventCalendarId") int eventCalendarId,  @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date startingDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.officialHolidayService.updateByStartingDate(eventCalendarId, startingDateUpdate);
	}
	
	@PutMapping("/updateByEndDate/{eventCalendarId}")
	public Result updateByEndDate(@RequestParam(value = "eventCalendarId") int eventCalendarId,  @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date endDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.officialHolidayService.updateByEndDate(eventCalendarId, endDateUpdate);
	}
	
	@DeleteMapping("/deleteByOfficialHolidayId/{eventCalendarId}")
	public Result deleteByOfficialHolidayId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.officialHolidayService.deleteByOfficialHolidayId(eventCalendarId);
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
