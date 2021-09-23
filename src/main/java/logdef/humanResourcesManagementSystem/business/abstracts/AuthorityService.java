package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Authority;

public interface AuthorityService {
	
	Result add(Authority authority);
	
	DataResult<List<Authority>> getAll(int pageNo, int pageSize);
	
	Authority getByAuthorityId(int authorityId);

}
