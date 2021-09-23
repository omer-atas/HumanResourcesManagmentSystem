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

import logdef.humanResourcesManagementSystem.business.abstracts.StudiedDepartmentService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.StudiedDepartment;

@RestController
@RequestMapping("/api/studiedDepartment")
@CrossOrigin
public class StudiedDepartmentController {
	
	private StudiedDepartmentService studiedDepartmentService;

	@Autowired
	public StudiedDepartmentController(StudiedDepartmentService studiedDepartmentService) {
		super();
		this.studiedDepartmentService = studiedDepartmentService;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody StudiedDepartment studiedDepartment) {
		return ResponseEntity.ok(this.studiedDepartmentService.add(studiedDepartment));
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return ResponseEntity.ok(this.studiedDepartmentService.getAll(pageNo, pageSize));
	}
	
	@GetMapping("/getByStudiedDepartmentId/{studiedDepartmentId}")
	public DataResult<StudiedDepartment> getByStudiedDepartmentId(@RequestParam(value = "studiedDepartmentId") int studiedDepartmentId) {
		return this.studiedDepartmentService.getByStudiedDepartmentId(studiedDepartmentId);
	}
	
	@GetMapping("/getByStudiedDepartment/{studiedDepartment}")
	public DataResult<StudiedDepartment> getByStudiedDepartment(@RequestParam(value = "studiedDepartment") String studiedDepartment) {
		return this.studiedDepartmentService.getByStudiedDepartment(studiedDepartment);
	}
	
	@PutMapping("/updateByStudiedDepartment/{studiedSectionId}")
	public Result updateByStudiedDepartment(@RequestParam(value = "studiedDepartmentId") int studiedDepartmentId,@RequestParam(value = "studiedDepartment") String studiedDepartmentUpdate) {
		return this.studiedDepartmentService.updateByStudiedDepartment(studiedDepartmentId, studiedDepartmentUpdate);
	}
	
	@DeleteMapping("/deleteByStudiedDepartmentId/{studiedSectionId}")
	public Result deleteByStudiedDepartmentId(@RequestParam(value = "studiedDepartmentId") int studiedDepartmentId) {
		return this.studiedDepartmentService.deleteByStudiedDepartmentId(studiedDepartmentId);
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
