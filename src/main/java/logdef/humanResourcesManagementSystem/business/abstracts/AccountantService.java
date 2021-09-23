package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Accountant;

public interface AccountantService {
	
	Result add(Accountant accountant); 
	
	DataResult<List<Accountant>> getAll(int pageNo, int pageSize);
	
	DataResult<Accountant> getByEmployeeId(int employeeId);
	
	Result deleteByAccountantId(int employeeId);

}
