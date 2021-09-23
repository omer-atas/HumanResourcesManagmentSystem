package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;


import java.util.List;


import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Employees;

public interface EmployeesService {
	
	DataResult<List<Employees>> getAll(int pageNo, int pageSize);
	
	Result add(Employees employees);
	
	DataResult<Employees> getByIdentityNumber(String identityNumber);
	
	DataResult<Employees> getByEmployeeId(int employeeId);
	
	DataResult<List<Employees>> getByFirstNameContains(String firstName);
	
	DataResult<List<Employees>> getByFirstNameStartsWith(String firstName);
	
	DataResult<List<Employees>> findByFirstNameStartsWith(String firstName);
	
	Result updateByFirstName(String identityNumber,String firstNameUpdate);
	
	Result updateByLastName(String identityNumber,String lastNameUpdate);
	
	Result updateByMaritalStatus(String identityNumber,String maritalStatusUpdate);
	
	Result updateByMilitaryStatus(String identityNumber,String militaryStatusUpdate);
	
	Result updateByChildrenCount(String identityNumber,String childrenCountUpdate);
	
	Result updateByBloodGroup(String identityNumber,String bloodGroupUpdate);
	
	Result updateByNationality(String identityNumber,String nationalityUpdate);
	
	Result updateByGender(String identityNumber,String genderUpdate);
	
	Result updateByBirthday(String identityNumber,Date birthdayUpdate);
	
	Result deleteByEmployeeId(int employeeId);
}
