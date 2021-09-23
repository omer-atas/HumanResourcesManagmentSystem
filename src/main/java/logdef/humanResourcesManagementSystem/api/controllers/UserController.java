package logdef.humanResourcesManagementSystem.api.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import logdef.humanResourcesManagementSystem.business.abstracts.UserService;
import logdef.humanResourcesManagementSystem.core.entities.concretes.Users;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.dtos.UserWithEmployeeDto;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Users user,@RequestParam(value = "roleName") String roleName) {
		return ResponseEntity.ok(this.userService.add(user,roleName));
	}
	
	@GetMapping("/getall")
	public DataResult<List<Users>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
		return this.userService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/findByEmail")
	public DataResult<Users> findByEmail(@RequestParam(value = "email") String email) {
		return this.userService.findByEmail(email);
	}
	
	@GetMapping("/getByUserId")
	public DataResult<Users> getByUserId(@RequestParam(value = "userId") int userId) {
		return this.userService.getByUserId(userId);
	}
	
	@GetMapping("/getUserWithEmployeeDtoDetails")
	public DataResult<List<UserWithEmployeeDto>> getUserWithEmployeeDtoDetails() {
		return this.userService.getUserWithEmployeeDtoDetails();
	}
	
	@DeleteMapping("/deleteByUserId/{email}")
	public Result deleteByUserId(@RequestParam(value = "userId") int userId) {
		return this.userService.deleteByUserId(userId);
	}
	
	@DeleteMapping("/deleteByEmail/{email}")
	public Result deleteByEmail(@RequestParam(value = "email") String email) {
		return this.userService.deleteByEmail(email);
	}
	
	@PutMapping("/updateByEmail/{email}")
	public Result updateByEmail(@RequestParam(value = "email") String email,@RequestParam(value = "emailUpdate") String emailUpdate) {
		return this.userService.updateByEmail(email, emailUpdate);
	}
	
	@PutMapping("/updateByPassword/{email}")
	public Result updateByPassword(@RequestParam(value = "email") String email ,
			@RequestParam(value = "passwordUpdate") String passwordUpdate) {
		return this.userService.updateByPassword(email,passwordUpdate);
	}
	
	@GetMapping("/getByEmailAndPassword")
	public DataResult<Users> getByEmailAndPassword(@RequestParam(value = "email") String email,
													  @RequestParam(value = "password") String password) {
		return this.userService.getByEmailAndPassword(email, password);
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
