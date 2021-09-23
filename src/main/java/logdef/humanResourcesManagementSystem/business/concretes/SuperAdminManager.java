package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.SuperAdminService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.RoleDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.SuperAdminDao;
import logdef.humanResourcesManagementSystem.entities.concretes.SuperAdmin;


@Service

public class SuperAdminManager implements SuperAdminService {
	
	private SuperAdminDao superAdminDao;
	private RoleDao roleDao;
	
	private String superAdminRole= "SUPER_ADMİN";
	
	@Autowired
	public SuperAdminManager(SuperAdminDao superAdminDao,RoleDao roleDao) {
		super();
		this.superAdminDao = superAdminDao;
		this.roleDao = roleDao;
	}

	@Override
	public Result add(SuperAdmin superAdmin) {
		
		if(this.superAdminDao.getByEmail(superAdmin.getEmail()) != null) {
			return new ErrorResult("Someone already registered with this email..");
		}
		
		superAdmin.setRoleSuperAdmin(this.roleDao.getByRoleName(superAdminRole));
		
		this.superAdminDao.save(superAdmin);
		return new SuccessResult(superAdmin.getFirstName() + " " + 
										superAdmin.getLastName() + " added");
	}

	@Override
	public DataResult<List<SuperAdmin>> getAll(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<SuperAdmin>>(this.superAdminDao.findAll(pageable).getContent(), "Super admin listed");
	}

}
