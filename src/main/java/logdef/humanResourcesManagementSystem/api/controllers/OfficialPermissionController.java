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

import logdef.humanResourcesManagementSystem.business.abstracts.OfficialPermissionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialPermission;

@RestController
@RequestMapping("/api/officialPermission")
@CrossOrigin
public class OfficialPermissionController {
	
	private OfficialPermissionService officialPermissionService;

	@Autowired
	public OfficialPermissionController(OfficialPermissionService officialPermissionService) {
		super();
		this.officialPermissionService = officialPermissionService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody OfficialPermission officialPermission) {
		return ResponseEntity.ok(this.officialPermissionService.add(officialPermission));
	}
	
	@GetMapping("/getall")
	public DataResult<List<OfficialPermission>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.officialPermissionService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByPermissionTypeId")
	public DataResult<OfficialPermission> getByPermissionTypeId(@RequestParam(value = "permissionTypeId") int permissionTypeId) {
		return this.getByPermissionTypeId(permissionTypeId);
	}
	
	@GetMapping("/getByOfficialPermissionName")
	public DataResult<OfficialPermission> getByOfficialPermissionName(@RequestParam(value = "officialPermissionName") String officialPermissionName) {
		return this.officialPermissionService.getByOfficialPermissionName(officialPermissionName);
	}
	
	@PutMapping("/updateByOfficialPermissionName/{permissionTypeId}")
	public Result updateByOfficialPermissionName(@RequestParam(value = "permissionTypeId") int permissionTypeId, @RequestParam(value = "officialPermissionNameUpdate") String officialPermissionNameUpdate) {
		return this.officialPermissionService.updateByOfficialPermissionName(permissionTypeId, officialPermissionNameUpdate);
	}
	
	@PutMapping("/updateByPermissionTypeName/{permissionTypeId}")
	public Result updateByPermissionTypeName(@RequestParam(value = "permissionTypeId") int permissionTypeId,@RequestParam(value = "permissionTypeId") String permissionTypeNameUpdate) {
		return this.officialPermissionService.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
	}
	
	@PutMapping("/updateByStartingDate/{permissionTypeId}")
	public Result updateByStartingDate(@RequestParam(value = "permissionTypeId") int permissionTypeId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date startingDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		return this.officialPermissionService.updateByStartingDate(permissionTypeId, startingDateUpdate);
	}
	
	@PutMapping("/updateByEndDate/{permissionTypeId}")
	public Result updateByEndDate(@RequestParam(value = "permissionTypeId") int permissionTypeId, @RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date endDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.officialPermissionService.updateByEndDate(permissionTypeId, endDateUpdate);
	}
	
	@DeleteMapping("/deleteByOfficialPermissionId/{permissionTypeId}")
	public Result deleteByOfficialPermissionId(@RequestParam(value = "permissionTypeId") int permissionTypeId) {
		return this.officialPermissionService.deleteByOfficialPermissionId(permissionTypeId);
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
