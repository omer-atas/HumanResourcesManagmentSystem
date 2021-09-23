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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import logdef.humanResourcesManagementSystem.business.abstracts.EmployessAuthoritiesService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployessAuthorities;

@RestController
@RequestMapping("/api/employessAuthorities")
@CrossOrigin
public class EmployessAuthoritiesController {
	
	private EmployessAuthoritiesService employessAuthoritiesService;

	@Autowired
	public EmployessAuthoritiesController(EmployessAuthoritiesService employessAuthoritiesService) {
		super();
		this.employessAuthoritiesService = employessAuthoritiesService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody EmployessAuthorities employessAuthorities) {
		return ResponseEntity.ok(this.employessAuthoritiesService.add(employessAuthorities));
	}
	
	@GetMapping("/getall")
	public DataResult<List<EmployessAuthorities>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.employessAuthoritiesService.getAll(pageNo, pageSize);
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
