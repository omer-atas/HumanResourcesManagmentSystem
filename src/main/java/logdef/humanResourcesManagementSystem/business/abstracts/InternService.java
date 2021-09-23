package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;
import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;

public interface InternService {
	
	DataResult<List<Intern>> getAll(int pageNo, int pageSize);
	
	Result add(Intern intern);
	
	DataResult<Intern> getByEmail(String email);
	
	DataResult<Intern> getByEmailAndPassword(String email,String password);
	
	Result updateByFirstName(int internId,String firstNameUpdate);
	
	Result updateByLastName(int internId,String lastNameUpdate);
	
	Result updateByEmail(int internId,String emailUpdate);
	
	Result updateByAddress(int internId,String addressUpdate);
	
	Result updateByTelephoneNumber(int internId,String telephoneNumberUpdate);
	
	Result updateByInternshipStartedDate(int internId,Date internshipStartedDateUpdate);
	
	Result updateByInternshipEndDate(int internId,Date internshipEndDateUpdate);
	
	Result deleteByInternId(int internId);

}
