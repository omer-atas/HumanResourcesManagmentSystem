package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.EmployeesService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeesDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Employees;

@Service
public class EmployeesManager implements EmployeesService{

	private EmployeesDao employeesDao;
	 
	@Autowired	
	public EmployeesManager(EmployeesDao employeesDao) {
		super();
		this.employeesDao = employeesDao;
	}

	@Override
	public DataResult<List<Employees>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Employees>>
									(this.employeesDao.findAll(pageable).getContent(), "Employees listed..");
	}
	

	@Override
	public Result add(Employees employees) {

		this.employeesDao.save(employees);
		return new SuccessResult("Employees added..");
	}

	
	
	@Override
	public DataResult<Employees> getByEmployeeId(int employeeId) {
		return new SuccessDataResult<Employees>
				(this.employeesDao.getByEmployeeId(employeeId),"Employee with employee id "+
																					employeeId +" found..");
	}
	
	@Override
	public DataResult<Employees> getByIdentityNumber(String identityNumber) {
		return new SuccessDataResult<Employees>(this.employeesDao.getByIdentityNumber(identityNumber),identityNumber + " bulundu...");
	}
	
	public boolean checkEmployeeId(int employeeId) {
		
		if(this.employeesDao.getByEmployeeId(employeeId) == null) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkIdentityNumber(String identityNumber) {
		
		if(this.employeesDao.getByIdentityNumber(identityNumber) != null) {
			return false;
		}
		
		return true;
	}
	
	public Result updateByFirstName(String identityNumber, String firstNameUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByFirstName(identityNumber, firstNameUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + firstNameUpdate + " )");
	}

	@Override
	public Result updateByLastName(String identityNumber, String lastNameUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByLastName(identityNumber, lastNameUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + lastNameUpdate + " )");
	}

	@Override
	public Result updateByMaritalStatus(String identityNumber, String maritalStatusUpdate) {
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByMaritalStatus(identityNumber, maritalStatusUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + maritalStatusUpdate + " )");
	}

	@Override
	public Result updateByMilitaryStatus(String identityNumber, String militaryStatusUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByMilitaryStatus(identityNumber, militaryStatusUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + militaryStatusUpdate + " )");
	}

	@Override
	public Result updateByChildrenCount(String identityNumber, String childrenCountUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByChildrenCount(identityNumber, childrenCountUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + childrenCountUpdate + " )");
	}

	@Override
	public Result updateByBloodGroup(String identityNumber, String bloodGroupUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByBloodGroup(identityNumber, bloodGroupUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + bloodGroupUpdate + " )");
	}

	@Override
	public Result updateByNationality(String identityNumber, String nationalityUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByNationality(identityNumber, nationalityUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + nationalityUpdate + " )");
	}

	@Override
	public Result updateByGender(String identityNumber, String genderUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByGender(identityNumber, genderUpdate);
		
		
		return new SuccessResult(identityNumber + " updated.." + "( " + genderUpdate + " )");
		
		
	}

	@Override
	public Result updateByBirthday(String identityNumber, Date birthdayUpdate) {
		
		if(checkIdentityNumber(identityNumber) == true) {
			return new ErrorResult("There is no such identification number.");
		}
		
		this.employeesDao.updateByBirthday(identityNumber, birthdayUpdate);
		return new SuccessResult(identityNumber + " updated.." + "( " + birthdayUpdate + " )");
	}

	@Override
	public Result deleteByEmployeeId(int employeeId) {
		
		if(checkEmployeeId(employeeId) == false) {
			return new ErrorResult("No such employee was found.");
		}
		
		System.out.println("The employee with the employee" + employeeId + " is deleted...");
		this.employeesDao.deleteByEmployeeId(employeeId);	
		return new SuccessResult("Employees deleted..");
	}

	@Override
	public DataResult<List<Employees>> getByFirstNameContains(String firstName) {
		return new SuccessDataResult<List<Employees>>(this.employeesDao.getByFirstNameContains(firstName), firstName + " bulundu..");
	}

	@Override
	public DataResult<List<Employees>> getByFirstNameStartsWith(String firstName) {
		return new SuccessDataResult<List<Employees>>(this.employeesDao.getByFirstNameStartsWith(firstName), firstName + " bulundu..");
	}

	@Override
	public DataResult<List<Employees>> findByFirstNameStartsWith(String firstName) {
		return new SuccessDataResult<List<Employees>>(this.employeesDao.findByFirstNameStartsWith(firstName));
	}

	

	

}
