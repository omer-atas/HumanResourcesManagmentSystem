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

import logdef.humanResourcesManagementSystem.business.abstracts.InternService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;

@RestController
@RequestMapping("/api/intern")
@CrossOrigin
public class InternController {
	
	private InternService internService;

	@Autowired
	public InternController(InternService internService) {
		super();
		this.internService = internService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Intern intern) {
		return ResponseEntity.ok(this.internService.add(intern));
	}
	
	@GetMapping("/getall")
	public DataResult<List<Intern>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.internService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByEmail")
	public DataResult<Intern> getByEmail(@RequestParam(value = "email") String email) {
		return this.internService.getByEmail(email);
	}
	
	@GetMapping("/getByEmailAndPassword")
	public DataResult<Intern> getByEmailAndPassword(@RequestParam(value = "email")String email,@RequestParam(value = "password") String password) {
		return this.internService.getByEmailAndPassword(email, password);
	}
	
	@PutMapping("/updateByFirstName/{internId}")
	public Result updateByFirstName(@RequestParam(value = "internId") int internId,@RequestParam(value = "firstNameUpdate") String firstNameUpdate) {
		return this.internService.updateByFirstName(internId, firstNameUpdate);
	}
	
	@PutMapping("/updateByLastName/{internId}")
	public Result updateByLastName(@RequestParam(value = "internId") int internId, @RequestParam(value = "lastNameUpdate") String lastNameUpdate) {
		return this.internService.updateByLastName(internId, lastNameUpdate);
	}
	
	@PutMapping("/updateByEmail/{internId}")
	public Result updateByEmail(@RequestParam(value = "internId") int internId,@RequestParam(value = "emailUpdate") String emailUpdate) {
		return this.internService.updateByEmail(internId, emailUpdate);
	}
	
	@PutMapping("/updateByAddress/{internId}")
	public Result updateByAddress(@RequestParam(value = "internId") int internId,@RequestParam(value = "addressUpdate") String addressUpdate) {
		return this.internService.updateByAddress(internId, addressUpdate);
	}
	
	@PutMapping("/updateByTelephoneNumber/{internId}")
	public Result updateByTelephoneNumber(@RequestParam(value = "internId") int internId,@RequestParam(value = "telephoneNumberUpdate") String telephoneNumberUpdate) {
		return this.internService.updateByTelephoneNumber(internId, telephoneNumberUpdate);
	}
	
	@PutMapping("/updateByInternshipStartedDate/{internId}")
	public Result updateByInternshipStartedDate(@RequestParam(value = "internId") int internId,
			@RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date internshipStartedDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		return this.internService.updateByInternshipStartedDate(internId, internshipStartedDateUpdate);
	}
	
	@PutMapping("/updateByInternshipEndDate/{internId}")
	public Result updateByInternshipEndDate(@RequestParam(value = "internId") int internId,
			@RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date internshipEndDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		
		return this.internService.updateByInternshipEndDate(internId, internshipEndDateUpdate);
	}
	
	@DeleteMapping("/deleteByInternId/{internId}")
	public Result deleteByInternId(@RequestParam(value = "internId") int internId) {
		return this.internService.deleteByInternId(internId);
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
