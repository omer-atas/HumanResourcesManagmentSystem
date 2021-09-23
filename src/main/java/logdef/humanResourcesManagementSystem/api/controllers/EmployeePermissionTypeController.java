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

import logdef.humanResourcesManagementSystem.business.abstracts.EmployeePermissionTypeService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployeePermissionType;

@RestController
@RequestMapping("/api/employeePermissionType")
@CrossOrigin
public class EmployeePermissionTypeController {
	
	private EmployeePermissionTypeService employeePermissionTypeService;

	@Autowired
	public EmployeePermissionTypeController(EmployeePermissionTypeService employeePermissionTypeService) {
		super();
		this.employeePermissionTypeService = employeePermissionTypeService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody EmployeePermissionType employeePermissionType) {
		return ResponseEntity.ok(this.employeePermissionTypeService.add(employeePermissionType));
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<EmployeePermissionType>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.employeePermissionTypeService.getAll(pageNo, pageSize);
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
