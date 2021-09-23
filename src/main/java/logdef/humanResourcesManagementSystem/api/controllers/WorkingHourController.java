package logdef.humanResourcesManagementSystem.api.controllers;

import java.util.HashMap;
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

import logdef.humanResourcesManagementSystem.business.abstracts.WorkingHourService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.WorkingHour;

@RestController
@RequestMapping("/api/workingHour")
@CrossOrigin
public class WorkingHourController {

	private WorkingHourService workingHourService;

	@Autowired
	public WorkingHourController(WorkingHourService workingHourService) {
		super();
		this.workingHourService = workingHourService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody WorkingHour workingHour) {
		return ResponseEntity.ok(this.workingHourService.add(workingHour));
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return ResponseEntity.ok(this.workingHourService.getAll(pageNo, pageSize));
	}
	
	@GetMapping("/getByWorkingHour/{workingHour}")
	public DataResult<WorkingHour> getByWorkingHour(@RequestParam(value = "workingHour") String workingHour) {
		return this.workingHourService.getByWorkingHour(workingHour);
	}
	
	@GetMapping("/getByWorkingHourId/{workingHourId}")
	public DataResult<WorkingHour> getByWorkingHourId(@RequestParam(value = "workingHourId") int workingHourId) {
		return this.workingHourService.getByWorkingHourId(workingHourId);
	}
	
	@PutMapping("/updateByWorkingHour/{workingHourId}")
	public Result updateByWorkingHour(@RequestParam(value = "workingHourId") int workingHourId,@RequestParam(value = "workingHourUpdate") String workingHourUpdate) {
		return this.workingHourService.updateByWorkingHour(workingHourId, workingHourUpdate);
	}
	
	@DeleteMapping("/deleteByWorkingHourId/{workingHourId}")
	public Result deleteByWorkingHourId(@RequestParam(value = "workingHourId") int workingHourId) {
		return this.workingHourService.deleteByWorkingHourId(workingHourId);
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
