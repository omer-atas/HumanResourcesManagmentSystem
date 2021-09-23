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

import logdef.humanResourcesManagementSystem.business.abstracts.InCompanyActivityService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyActivity;

@RestController
@RequestMapping("/api/inCompanyActivity")
@CrossOrigin
public class InCompanyActivityController {
	
	private InCompanyActivityService inCompanyActivityService;

	@Autowired
	public InCompanyActivityController(InCompanyActivityService inCompanyActivityService) {
		super();
		this.inCompanyActivityService = inCompanyActivityService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody InCompanyActivity inCompanyActivity) {
		return ResponseEntity.ok(this.inCompanyActivityService.add(inCompanyActivity));
	}
	
	@GetMapping("/getall")
	public DataResult<List<InCompanyActivity>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.inCompanyActivityService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByEventCalendarId")
	public DataResult<InCompanyActivity> getByEventCalendarId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.inCompanyActivityService.getByEventCalendarId(eventCalendarId);
	}
	
	@GetMapping("/getByInCompanyActivityName")
	public DataResult<InCompanyActivity> getByInCompanyActivityName(@RequestParam(value = "inCompanyActivityName") String inCompanyActivityName) {
		return this.inCompanyActivityService.getByInCompanyActivityName(inCompanyActivityName);
	}
	
	@PutMapping("/updateByEventCalendarName/{eventCalendarId}")
	public Result updateByEventCalendarName(@RequestParam(value = "eventCalendarId") int eventCalendarId,@RequestParam(value = "eventCalendarNameUpdate") String eventCalendarNameUpdate) {
		return this.inCompanyActivityService.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
	}
	
	@PutMapping("/updateByInCompanyPermissionName/{eventCalendarId}")
	public Result updateByInCompanyPermissionName(@RequestParam(value = "eventCalendarId") int eventCalendarId,@RequestParam(value = "inCompanyActivityNameUpdate") String inCompanyActivityNameUpdate) {
		return this.inCompanyActivityService.updateByInCompanyPermissionName(eventCalendarId, inCompanyActivityNameUpdate);
	}
	
	@PutMapping("/updateByStartingDate/{eventCalendarId}")
	public Result updateByStartingDate(@RequestParam(value = "eventCalendarId") int eventCalendarId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date startingDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.inCompanyActivityService.updateByStartingDate(eventCalendarId, startingDateUpdate);
	}
	
	@PutMapping("/updateByEndDate/{eventCalendarId}")
	public Result updateByEndDate(@RequestParam(value = "eventCalendarId") int eventCalendarId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date endDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		return this.inCompanyActivityService.updateByEndDate(eventCalendarId, endDateUpdate);
	}
	
	@DeleteMapping("/deleteByInCompanyActivityId/{eventCalendarId}")
	public Result deleteByInCompanyActivityId(@RequestParam(value = "eventCalendarId") int eventCalendarId) {
		return this.inCompanyActivityService.deleteByInCompanyActivityId(eventCalendarId);
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
