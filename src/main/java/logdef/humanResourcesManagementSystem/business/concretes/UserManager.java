package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.UserService;
import logdef.humanResourcesManagementSystem.core.dataAccess.abstracts.UserDao;
import logdef.humanResourcesManagementSystem.core.entities.concretes.Users;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeesDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.RoleDao;
import logdef.humanResourcesManagementSystem.entities.dtos.UserWithEmployeeDto;

@Service
public class UserManager implements UserService{
	
	private UserDao userDao;
	private RoleDao roleDao;
	private EmployeesDao employeesDao;
	
	@Autowired
	public UserManager(UserDao userDao,RoleDao roleDao, EmployeesDao employeesDao) {
		super();
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.employeesDao = employeesDao;
	}

	@Override
	public Result add(Users user,String roleName) {
	
		
		if (user.getEmployeesUser().getEmployeeId() == 0 ||
				this.employeesDao.getByEmployeeId(user.getEmployeesUser().getEmployeeId())== null ||
				this.roleDao.getByRoleName(roleName.toUpperCase()) == null){
			return new ErrorResult("İlişkiyi giriniz....");
		}
		
		if(this.userDao.getByEmployeeId(user.getEmployeesUser().getEmployeeId()) != null) {
			return new ErrorResult("İlişkili olmayan çalışan kimliği giriniz...");
		}
		
		
		if(checkEmail(user.getEmail()) == true) {
			return new ErrorResult("Someone already registered with this email..");
		}
		
		user.setRole(this.roleDao.getByRoleName(roleName.toUpperCase())); // Yönetici - Çalışan - Stajyer ekleme
		this.userDao.save(user);
		return new SuccessResult(user.getEmail() + " added..");
	} 
	
	@Override
	public DataResult<List<Users>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Users>>(this.userDao.findAll(pageable).getContent(), "User listed..");
	}

	@Override
	public DataResult<Users> findByEmail(String email) {
		return new SuccessDataResult<Users>(this.userDao.findByEmail(email), "User found..");
	}
	
	public boolean checkEmail(String email) {
		if(this.userDao.findByEmail(email) == null) {
			return false;
		}
		return true;
	}
	
	public boolean checkUserId(int userId) {
		if(this.userDao.getByUserId(userId) == null) {
			return false;
		}
		return true;
	}

	@Override
	public DataResult<Users> getByUserId(int userId) {
		if(checkUserId(userId) == false) {
			return new ErrorDataResult<Users>(this.userDao.getByUserId(userId),
																	"User with this "+userId +" not found");
		}
		
		if(this.userDao.getByUserId(userId).getRole().getRoleName().equals("YÖNETİCİ".toUpperCase())) {
			System.out.println("Yönetici");
		}
		return new SuccessDataResult<Users>(this.userDao.getByUserId(userId),
																		"User : "+ userId +" found..");
	}

	@Override
	public Result deleteByUserId(int userId) {
		
		if(checkUserId(userId) == false) {
			return new ErrorResult("No such user was found.");
		}
		
		System.out.println("The users with the user " + userId + " is deleted...");
		this.userDao.deleteByUserId(userId);	
		return new SuccessResult("User deleted..");
	}

	@Override
	public Result deleteByEmail(String email) {
		
		if(checkEmail(email) == false) {
			return new ErrorResult("No such email was found.");
		}
		
		System.out.println("The users with the user " + email + " is deleted...");
		this.userDao.deleteByEmail(email);	
		return new SuccessResult("User deleted..");
	}

	@Override
	public Result updateByEmail(String email, String emailUpdate) {
		
		if(checkEmail(email) == false) {
			return new ErrorResult("No such email was found.");
		}
		
		this.userDao.updateByEmail(email, emailUpdate);
		return new SuccessResult(email + " updated.." + "( " + emailUpdate + " )");
	}

	@Override
	public DataResult<Users> getByEmailAndPassword(String email, String password) {
		return new SuccessDataResult<Users>(this.userDao.getByEmailAndPassword(email, password),"");
	}
	
	@Override
	public Result updateByPassword(String email, String passwordUpdate) {
		
		if(checkEmail(email) == false) {
			return new ErrorResult("No such email was found.");
		}
		
		this.userDao.updateByPassword(email,passwordUpdate);
		return new SuccessResult("Password updated...");
		
	}

	@Override
	public DataResult<List<UserWithEmployeeDto>> getUserWithEmployeeDtoDetails() {
		return new SuccessDataResult<List<UserWithEmployeeDto>>(this.userDao.getUserWithEmployeeDtoDetails(), "bulundu..");
	}




}
