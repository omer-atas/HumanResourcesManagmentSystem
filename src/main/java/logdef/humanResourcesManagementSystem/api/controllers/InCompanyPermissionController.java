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

import logdef.humanResourcesManagementSystem.business.abstracts.InCompanyPermissionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyPermission;

@RestController
@RequestMapping("/api/companyPermission")
@CrossOrigin
public class InCompanyPermissionController {
	
	private InCompanyPermissionService inCompanyPermissionService;
	
	@Autowired	
	public InCompanyPermissionController(InCompanyPermissionService inCompanyPermissionService) {
		super();
		this.inCompanyPermissionService = inCompanyPermissionService;
	}


	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody InCompanyPermission inCompanyPermission) {
		return ResponseEntity.ok(this.inCompanyPermissionService.add(inCompanyPermission));
	}


	@GetMapping("/getall")
	public DataResult<List<InCompanyPermission>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.inCompanyPermissionService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByPermissionTypeId")
	public DataResult<InCompanyPermission> getByPermissionTypeId(@RequestParam(value = "permissionTypeId") int permissionTypeId) {
		return this.inCompanyPermissionService.getByPermissionTypeId(permissionTypeId);
	}
	
	@GetMapping("/getByInCompanyPermissionName")
	public DataResult<InCompanyPermission> getByInCompanyPermissionName(@RequestParam(value = "permissionTypeId") int permissionTypeId,@RequestParam(value = "inCompanyPermissionName") String inCompanyPermissionName) {
		return this.inCompanyPermissionService.getByInCompanyPermissionName(inCompanyPermissionName);
	}
	
	@PutMapping("/updateByInCompanyPermissionName/{permissionTypeId}")
	public Result updateByInCompanyPermissionName(@RequestParam(value = "permissionTypeId") int permissionTypeId,@RequestParam(value = "inCompanyPermissionNameUpdate") String inCompanyPermissionNameUpdate) {
		return this.inCompanyPermissionService.updateByInCompanyPermissionName(permissionTypeId, inCompanyPermissionNameUpdate);
	}
	
	@PutMapping("/updateByIsVerified/{permissionTypeId}")
	public Result updateByIsVerified(@RequestParam(value = "permissionTypeId") int permissionTypeId,@RequestParam(value = "isVerifiedUpdate") boolean isVerifiedUpdate) {
		return this.inCompanyPermissionService.updateByIsVerified(permissionTypeId, isVerifiedUpdate);
	}
	
	@PutMapping("/updateByStartingDate/{permissionTypeId}")
	public Result updateByStartingDate(@RequestParam(value = "permissionTypeId") int permissionTypeId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date startingDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.inCompanyPermissionService.updateByStartingDate(permissionTypeId, startingDateUpdate);
	}
	
	@PutMapping("/updateByEndDate/{permissionTypeId}")
	public Result updateByEndDate(@RequestParam(value = "permissionTypeId")  int permissionTypeId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date endDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.inCompanyPermissionService.updateByEndDate(permissionTypeId, endDateUpdate);
	}
	
	@PutMapping("/updateByPermissionTypeName/{permissionTypeId}")
	public Result updateByPermissionTypeName(@RequestParam(value = "permissionTypeId") int permissionTypeId, @RequestParam(value = "permissionTypeNameUpdate") String permissionTypeNameUpdate) {
		return this.inCompanyPermissionService.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
	}
	
	@DeleteMapping("/deleteByInCompanyPermissionId/{permissionTypeId}")
	public Result deleteByInCompanyPermissionId(@RequestParam(value = "permissionTypeId")  int permissionTypeId) {
		return this.inCompanyPermissionService.deleteByInCompanyPermissionId(permissionTypeId);
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
