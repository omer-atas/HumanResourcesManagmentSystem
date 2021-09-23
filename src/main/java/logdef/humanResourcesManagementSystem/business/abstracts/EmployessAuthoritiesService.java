package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployessAuthorities;

public interface EmployessAuthoritiesService {
	
	Result add(EmployessAuthorities employessAuthorities);
	
	DataResult<List<EmployessAuthorities>> getAll(int pageNo, int pageSize);

}
