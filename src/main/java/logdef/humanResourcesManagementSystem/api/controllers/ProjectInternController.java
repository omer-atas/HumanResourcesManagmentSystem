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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import logdef.humanResourcesManagementSystem.business.abstracts.ProjectInternService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.ProjectIntern;

@RestController
@RequestMapping("/api/projectIntern")
@CrossOrigin
public class ProjectInternController {

	private ProjectInternService projectInternService;

	@Autowired
	public ProjectInternController(ProjectInternService projectInternService) {
		super();
		this.projectInternService = projectInternService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody ProjectIntern projectIntern) {
		return ResponseEntity.ok(this.projectInternService.add(projectIntern));
	}
	
	@GetMapping("/getall")
	public DataResult<List<ProjectIntern>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.projectInternService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getByProjectInternId/{projectInternId}")
	public DataResult<ProjectIntern> getByProjectInternId(@RequestParam(value = "projectInternId") int projectInternId) {
		return this.projectInternService.getByProjectInternId(projectInternId);
	}
	
	@GetMapping("/getByProjectName/{projectName}")
	public DataResult<ProjectIntern> getByProjectName(@RequestParam(value = "projectName") String projectName) {
		return this.projectInternService.getByProjectName(projectName);
	}
	
	@GetMapping("/getBySourceCode/{sourceCode}")
	public DataResult<ProjectIntern> getBySourceCode(@RequestParam(value = "sourceCode") String sourceCode) {
		return this.projectInternService.getBySourceCode(sourceCode);
	}

	@PutMapping("/updateByFirstName/{projectInternId}")
	public Result updateByProjectName(@RequestParam(value = "projectInternId") int projectInternId,@RequestParam(value = "projectNameUpdate") String projectNameUpdate) {
		return this.projectInternService.updateByProjectName(projectInternId, projectNameUpdate);
	}
	
	@RequestMapping(value = "/updateBySourceCode/{projectInternId}", method = { RequestMethod.PUT })
	public Result updateBySourceCode(int projectInternId, String sourceCodeUpdate) {
		return this.projectInternService.updateBySourceCode(projectInternId, sourceCodeUpdate);
	}
	
	@DeleteMapping("/deleteByProjectInternId/{projectInternId}")
	public Result deleteByProjectInternId(@RequestParam(value = "projectInternId") int projectInternId) {
		return this.projectInternService.deleteByProjectInternId(projectInternId);
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
