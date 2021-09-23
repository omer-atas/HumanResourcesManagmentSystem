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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import logdef.humanResourcesManagementSystem.business.abstracts.EmployeesService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Employees;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeesControllers {
	
	private EmployeesService employeesService;

	@Autowired
	public EmployeesControllers(EmployeesService employeesService) {
		super();
		this.employeesService = employeesService;
	}	
		
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Employees employees) {
		return ResponseEntity.ok(this.employeesService.add(employees));
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employees>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.employeesService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getByEmployeeId/{employeeId}")
	public DataResult<Employees> getByEmployeeId(@RequestParam(value = "employeeId") int employeeId) {
		return this.employeesService.getByEmployeeId(employeeId);
	}
	
	@GetMapping("/getByFirstNameContains/{firstName}")
	public DataResult<List<Employees>> getByFirstNameContains(String firstName) {
		return this.employeesService.getByFirstNameContains(firstName);
	}
	
	@GetMapping("/getByFirstNameStartsWith/{firstName}")
	public DataResult<List<Employees>> getByFirstNameStartsWith(String firstName) {
		return this.employeesService.getByFirstNameStartsWith(firstName);
	}
	
	@GetMapping("/getByIdentityNumber")
	public DataResult<Employees> getByIdentityNumber(@RequestParam(value = "identityNumber") String identityNumber) {
		return this.employeesService.getByIdentityNumber(identityNumber);
	}

	@RequestMapping(value = "/findByFirstNameStartsWith/{firstName}", method = { RequestMethod.GET })
	public DataResult<List<Employees>> findByFirstNameStartsWith(String firstName) {
		return this.employeesService.findByFirstNameStartsWith(firstName);
	}
	
	@PutMapping("/updateByFirstName/{identityNumber}")
	public Result updateByFirstName(@RequestParam(value = "identityNumber") String identityNumber, @RequestParam(value = "firstNameUpdate") String firstNameUpdate) {
		return this.employeesService.updateByFirstName(identityNumber, firstNameUpdate);
	}
	
	@RequestMapping(value = "/updateByLastName/{identityNumber}", method = { RequestMethod.PUT })
	public Result updateByLastName(@RequestParam(value = "identityNumber")  String identityNumber,@RequestParam(value = "lastNameUpdate") String lastNameUpdate) {
		return this.employeesService.updateByLastName(identityNumber, lastNameUpdate);
	}
	
	@PutMapping("/updateByMaritalStatus/{identityNumber}")
	public Result updateByMaritalStatus(@RequestParam(value = "identityNumber") String identityNumber,@RequestParam(value = "maritalStatusUpdate") String maritalStatusUpdate) {
		return this.employeesService.updateByMaritalStatus(identityNumber, maritalStatusUpdate);
	}
	
	@PutMapping("/updateByMilitaryStatus/{identityNumber}")
	public Result updateByMilitaryStatus(@RequestParam(value = "identityNumber") String identityNumber,@RequestParam(value = "militaryStatusUpdate") String militaryStatusUpdate) {
		return this.employeesService.updateByMilitaryStatus(identityNumber, militaryStatusUpdate);
	}
	
	@PutMapping("/updateByChildrenCount/{identityNumber}")
	public Result updateByChildrenCount(@RequestParam(value = "identityNumber") String identityNumber,@RequestParam(value = "childrenCountUpdate") String childrenCountUpdate) {
		return this.employeesService.updateByChildrenCount(identityNumber, childrenCountUpdate);
	}
	
	@PutMapping("/updateByBloodGroup/{identityNumber}")
	public Result updateByBloodGroup(@RequestParam(value = "identityNumber") String identityNumber, @RequestParam(value = "bloodGroupUpdate") String bloodGroupUpdate) {
		return this.employeesService.updateByBloodGroup(identityNumber, bloodGroupUpdate);
	}
	
	@PutMapping("/updateByNationality/{identityNumber}")
	public Result updateByNationality(@RequestParam(value = "identityNumber") String identityNumber,@RequestParam(value = "nationalityUpdate") String nationalityUpdate) {
		return this.employeesService.updateByNationality(identityNumber, nationalityUpdate);
	}
	
	@PutMapping("/updateByGender/{identityNumber}")
	public Result updateByGender(@RequestParam(value = "identityNumber") String identityNumber,@RequestParam(value = "genderUpdate") String genderUpdate) {
		return this.employeesService.updateByGender(identityNumber, genderUpdate);
	}
	
	@PutMapping("/updateByBirthday/{identityNumber}")
	public Result updateByBirthday(@RequestParam(value = "identityNumber") String identityNumber,
			@RequestParam(value = "day") int day,
			@RequestParam(value = "mounth") int mounth,
			@RequestParam(value = "year") int year) {
		
		Date date = new Date( day +"/"+ mounth +"/"+ year);
		
		
		return this.employeesService.updateByBirthday(identityNumber, date);
	}
	
	@DeleteMapping("/deleteByEmployeeId/{employeeId}")
	public Result deleteByEmployeeId(@RequestParam(value = "employeeId") int employeeId) {
		return this.employeesService.deleteByEmployeeId(employeeId);
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
