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

import logdef.humanResourcesManagementSystem.business.abstracts.PersonalPermissionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyPermission;
import logdef.humanResourcesManagementSystem.entities.concretes.PersonalPermission;

@RestController
@RequestMapping("/api/ personalPermission")
@CrossOrigin
public class PersonalPermissionController {
	
	private PersonalPermissionService personalPermissionService;

	@Autowired
	public PersonalPermissionController(PersonalPermissionService personalPermissionService) {
		super();
		this.personalPermissionService = personalPermissionService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody PersonalPermission personalPermission) {
		return ResponseEntity.ok(this.personalPermissionService.add(personalPermission));
	}
	
	@GetMapping("/getAll")
	public DataResult<List<PersonalPermission>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.personalPermissionService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getByPermissionTypeId")
	public DataResult<PersonalPermission> getByPermissionTypeId(@RequestParam(value = "permissionTypeId") int permissionTypeId) {
		return this.personalPermissionService.getByPermissionTypeId(permissionTypeId);
	}
	
	@GetMapping("/getByPersonalPermissionName")
	public DataResult<PersonalPermission> getByPersonalPermissionName(@RequestParam(value = "personalPermissionName") String personalPermissionName) {
		return this.personalPermissionService.getByPersonalPermissionName(personalPermissionName);
	}
	
	@PutMapping("/updateByPersonalPermissionName/{permissionTypeId}")
	public Result updateByPersonalPermissionName(@RequestParam(value = "permissionTypeId") int permissionTypeId,@RequestParam(value = "personalPermissionNameUpdate") String personalPermissionNameUpdate) {
		return this.personalPermissionService.updateByPersonalPermissionName(permissionTypeId, personalPermissionNameUpdate);
	}
	
	@PutMapping("/updateByPermissionTypeName/{permissionTypeId}")
	public Result updateByPermissionTypeName(@RequestParam(value = "permissionTypeId") int permissionTypeId,@RequestParam(value = "permissionTypeNameUpdate") String permissionTypeNameUpdate) {
		return this.personalPermissionService.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
	}

	@PutMapping("/updateByStartingDate/{permissionTypeId}")
	public Result updateByStartingDate(int permissionTypeId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date startingDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.personalPermissionService.updateByStartingDate(permissionTypeId, startingDateUpdate);
	}
	
	@PutMapping("/updateByEndDate/{permissionTypeId}")
	public Result updateByEndDate(int permissionTypeId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date endDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.personalPermissionService.updateByEndDate(permissionTypeId, endDateUpdate);
	}
	
	@DeleteMapping("/deleteByPersonalPermissionId/{permissionTypeId}")
	public Result deleteByPersonalPermissionId(int permissionTypeId) {
		return this.personalPermissionService.deleteByPersonalPermissionId(permissionTypeId);
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
