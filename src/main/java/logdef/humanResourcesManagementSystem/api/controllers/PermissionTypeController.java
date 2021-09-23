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

import logdef.humanResourcesManagementSystem.business.abstracts.PermissionTypeService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.PermissionType;

@RestController
@RequestMapping("/api/permissionType")
@CrossOrigin
public class PermissionTypeController {
	
	private PermissionTypeService permissionTypeService;

	@Autowired
	public PermissionTypeController(PermissionTypeService permissionTypeService) {
		super();
		this.permissionTypeService = permissionTypeService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody PermissionType permissionType) {
		return ResponseEntity.ok(this.permissionTypeService.add(permissionType));
	}
	
	@GetMapping("/getall")
	public DataResult<List<PermissionType>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.permissionTypeService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByPermissionTypeId")
	public DataResult<PermissionType> getByPermissionTypeId(@RequestParam(value = "permissionTypeId") int permissionTypeId) {
		return this.permissionTypeService.getByPermissionTypeId(permissionTypeId);
	}
	
	@PutMapping("/updateByPermissionTypeName/{permissionTypeId}")
	public Result updateByPermissionTypeName(@RequestParam(value = "permissionTypeId") int permissionTypeId, @RequestParam(value = "permissionTypeNameUpdate") String permissionTypeNameUpdate) {
		return this.permissionTypeService.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
	}
	
	@DeleteMapping("/deleteByPermissionTypeId/{permissionTypeId}")
	public Result deleteByPermissionTypeId( @RequestParam(value = "permissionTypeId") int permissionTypeId) {
		return this.permissionTypeService.deleteByPermissionTypeId(permissionTypeId);
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
