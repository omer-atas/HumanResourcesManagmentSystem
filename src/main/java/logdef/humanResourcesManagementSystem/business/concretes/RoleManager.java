package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.RoleService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.RoleDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Role;

@Service

public class RoleManager implements RoleService{
	
	private RoleDao roleDao;
	
	@Autowired
	public RoleManager(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}
	

	@Override
	public Result add(Role role) {
		
		if(checkRoleName(role.getRoleName()) == true) {
			return new ErrorResult("Bu rol zaten mevcut..");
		}
		
		this.roleDao.save(role);
		return new SuccessResult(role.getRoleName() + " added..");
	}
	
	@Override
	public DataResult<List<Role>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Role>>(this.roleDao.findAll(pageable).getContent(), "Role listed..");
	}
	
	public DataResult<Role> message (String roleName){
		
		if(checkRoleName(roleName) == false) {
			return new ErrorDataResult<Role>("No such role name was found.");
		}else {
			return new SuccessDataResult<Role>("Success");
		}	
	}

	public boolean checkRoleName(String roleName) {
		
		if(this.roleDao.getByRoleName(roleName) == null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public DataResult<Role> getByRoleName(String roleName) {
		return new SuccessDataResult<Role>(this.roleDao.getByRoleName(roleName), "Role found." +
														"( " + roleName + " )");
	}


	@Override
	public DataResult<Role> getByRoleId(int roleId) {
		return new SuccessDataResult<Role>(this.roleDao.getByRoleId(roleId), "Role found." +
				"( " + roleId + " )");
	}

}
