package logdef.humanResourcesManagementSystem.core.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import logdef.humanResourcesManagementSystem.core.entities.concretes.Users;
import logdef.humanResourcesManagementSystem.entities.dtos.UserWithEmployeeDto;

public interface UserDao extends JpaRepository<Users, Integer> {
	
	Users findByEmail(String email);
	
	Users getByUserId(int userId);
	
	Users findByPassword(String password);
	
	@Query("From Users where email=:email and password=:password")
	Users getByEmailAndPassword(String email,String password);
	// SELECT * FROM public.users WHERE email = 'gustavo@hotmail.com' and password='LuisGustavo';
	
	@Query("From Users where employeesUser.employeeId=:employeeId")
	Users getByEmployeeId(int employeeId);
	
	@Transactional
	@Modifying
	@Query("DELETE From Users u where u.userId=:userId")
	void deleteByUserId(int userId);
	
	@Transactional
	@Modifying
	@Query("DELETE From Users u where u.email=:email")
	void deleteByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.email=:emailUpdate WHERE u.email=:email")
	void updateByEmail(String email,String emailUpdate);
	
	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.password=:passwordUpdate WHERE u.email=:email")
	void updateByPassword(@Param(value = "email") String email ,@Param(value = "passwordUpdate") String passwordUpdate);
	// UPDATE public.users	SET password='fenerbahcesporklubü1907'	WHERE email = 'gustavo@hotmail.com';

	
	@Query("Select new logdef.humanResourcesManagementSystem.entities.dtos.UserWithEmployeeDto"
			+ "(u.userId,e.firstName,e.lastName,e.identityNumber,u.email,u.password)"
			+ "From Employees e İnner Join e.employeesUser u")
	List<UserWithEmployeeDto> getUserWithEmployeeDtoDetails();
/*   SQL sorgusu :
 	 SELECT u.user_id, e.first_name, e.last_name, e.identity_number, u.email, u."password" FROM public.employees as e
	                  INNER JOIN public.users as u ON e.employee_id = u.employee_id;
*/
}
