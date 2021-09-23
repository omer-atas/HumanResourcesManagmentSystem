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

import logdef.humanResourcesManagementSystem.business.abstracts.GeneralInformationService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.GeneralInformation;

@RestController
@RequestMapping("/api/generalInformation")
@CrossOrigin
public class GeneralInformationController {
	
	private GeneralInformationService generalInformationService;

	@Autowired
	public GeneralInformationController(GeneralInformationService generalInformationService) {
		super();
		this.generalInformationService = generalInformationService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody GeneralInformation generalInformation) {
		return ResponseEntity.ok(this.generalInformationService.add(generalInformation));
	}
	
	@GetMapping("/getall")
	public DataResult<List<GeneralInformation>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.generalInformationService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getByGeneralInformationId")
	public DataResult<GeneralInformation> getByGeneralInformationId(@RequestParam(value = "generalInformationId") int generalInformationId) {
		return this.generalInformationService.getByGeneralInformationId(generalInformationId);
	}
	
	
	@PutMapping("/updateBySuperscription/{generalInformationId}")
	public Result updateBySuperscription(@RequestParam(value = "generalInformationId") int generalInformationId,@RequestParam(value = "superscriptionUpdate") String superscriptionUpdate) {
		return this.generalInformationService.updateBySuperscription(generalInformationId, superscriptionUpdate);
	}
	
	@PutMapping("/updateByTelephoneNumber/{generalInformationId}")
	public Result updateByTelephoneNumber(@RequestParam(value = "generalInformationId") int generalInformationId,@RequestParam(value = "telephoneNumberUpdate") String telephoneNumberUpdate) {
		return this.generalInformationService.updateByTelephoneNumber(generalInformationId, telephoneNumberUpdate);
	}
	
	@PutMapping("/updateByEmail/{generalInformationId}")
	public Result updateByEmail(@RequestParam(value = "generalInformationId") int generalInformationId,@RequestParam(value = "emailUpdate") String emailUpdate) {
		return this.generalInformationService.updateByEmail(generalInformationId, emailUpdate);
	}
	
	@PutMapping("/updateByStartedDate/{generalInformationId}")
	public Result updateByStartedDate(@RequestParam(value = "generalInformationId") int generalInformationId,
			@RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date startedDateUpdate = new Date( day +"/"+ mounth +"/"+ year);
		return this.generalInformationService.updateByStartedDate(generalInformationId, startedDateUpdate);
	}
	
	@PutMapping("/updateByWorkingHour/{generalInformationId}")
	public Result updateByWorkingHour(@RequestParam(value = "generalInformationId") int generalInformationId,@RequestParam(value = "workingHourIdUpdate")  int workingHourIdUpdate) {
		
		return this.generalInformationService.updateByWorkingHour(generalInformationId, workingHourIdUpdate);
	}
	
	@DeleteMapping("/deleteByGeneralInformationId/{generalInformationId}")
	public Result deleteByGeneralInformationId(@RequestParam(value = "generalInformationId") int generalInformationId) {
		return this.generalInformationService.deleteByGeneralInformationId(generalInformationId);
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
