package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.Role;

public interface RoleService {
	
	Result add(Role role); 
	
	DataResult<List<Role>> getAll(int pageNo, int pageSize);
	
	DataResult<Role> getByRoleName(String roleName);
	
	DataResult<Role> getByRoleId(int roleId);

}
