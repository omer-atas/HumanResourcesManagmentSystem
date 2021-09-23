package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;



import logdef.humanResourcesManagementSystem.core.entities.concretes.Users;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.dtos.UserWithEmployeeDto;

public interface UserService {
	
	Result add(Users user,String roleName); 
	
	DataResult<List<Users>> getAll(int pageNo, int pageSize);
	
	DataResult<Users> findByEmail(String email);
	
	DataResult<Users> getByUserId(int userId);
	
	DataResult<Users> getByEmailAndPassword(String email,String password);
	
	DataResult<List<UserWithEmployeeDto>> getUserWithEmployeeDtoDetails();

	Result deleteByUserId(int userId);
	
	Result deleteByEmail(String email);
	
	Result updateByEmail(String email,String emailUpdate);
	
	Result updateByPassword(String email,String passwordUpdate);

}
