package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.PersonalPermissionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.PersonalPermissionDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;
import logdef.humanResourcesManagementSystem.entities.concretes.PersonalPermission;

@Service
public class PersonalPermissionManager implements PersonalPermissionService{
	
	private PersonalPermissionDao personalPermissionDao;
	
	@Autowired
	public PersonalPermissionManager(PersonalPermissionDao personalPermissionDao) {
		super();
		this.personalPermissionDao = personalPermissionDao;
	}

	@Override
	public Result add(PersonalPermission personalPermission) {
		this.personalPermissionDao.save(personalPermission);
		return new SuccessResult("Personal permission of "+ personalPermission.getPermissionTypeName() +" added..");
	}

	@Override
	public DataResult<List<PersonalPermission>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<PersonalPermission>>(this.personalPermissionDao.findAll(pageable).getContent(), "Personal permission listed...");
	}
	

	public boolean checkPersonalPermissionId(int permissionTypeId) {
		
		if(this.personalPermissionDao.getByPermissionTypeId(permissionTypeId) == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkPersonalPermissionName(String personalPermissionName) {
		
		if(this.personalPermissionDao.getByPersonalPermissionName(personalPermissionName) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<PersonalPermission> getByPermissionTypeId(int permissionTypeId) {
		
		if(checkPersonalPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<PersonalPermission>(this.personalPermissionDao.
					getByPermissionTypeId(permissionTypeId),"Personal permission with this "+ permissionTypeId
					+" not found");
		}
		
		return new SuccessDataResult<PersonalPermission>(this.personalPermissionDao.
				getByPermissionTypeId(permissionTypeId), permissionTypeId +" found..");
	}

	@Override
	public DataResult<PersonalPermission> getByPersonalPermissionName(String personalPermissionName) {
		
		if(checkPersonalPermissionName(personalPermissionName) == false) {
			return new ErrorDataResult<PersonalPermission>(this.personalPermissionDao.
					getByPersonalPermissionName(personalPermissionName),"Personal permission name with this "+ personalPermissionName
					+" not found");
		}
		
		return new SuccessDataResult<PersonalPermission>(this.personalPermissionDao.
				getByPersonalPermissionName(personalPermissionName), personalPermissionName +" found..");
	}

	@Override
	public Result updateByPersonalPermissionName(int permissionTypeId, String personalPermissionNameUpdate) {
		
		if(checkPersonalPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such personal permission was found.");
		}
		
		this.personalPermissionDao.updateByPersonalPermissionName(permissionTypeId, personalPermissionNameUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + personalPermissionNameUpdate + " )");
	}

	@Override
	public Result updateByStartingDate(int permissionTypeId, Date startingDateUpdate) {
		
		if(checkPersonalPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such personal permission was found.");
		}
		
		this.personalPermissionDao.updateByStartingDate(permissionTypeId, startingDateUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + startingDateUpdate + " )");
	}

	@Override
	public Result updateByEndDate(int permissionTypeId, Date endDateUpdate) {
		
		if(checkPersonalPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such personal permission was found.");
		}
		
		this.personalPermissionDao.updateByEndDate(permissionTypeId, endDateUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + endDateUpdate + " )");
	}

	@Override
	public Result deleteByPersonalPermissionId(int permissionTypeId) {
		
		if(checkPersonalPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such personal permission was found.");
		}
		
		this.personalPermissionDao.deleteByPersonalPermissionId(permissionTypeId);
		return new SuccessResult(permissionTypeId + " deleted..");
	}

	@Override
	public Result updateByPermissionTypeName(int permissionTypeId, String permissionTypeNameUpdate) {
		
		if(checkPersonalPermissionId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such personal permission was found.");
		}
		
		this.personalPermissionDao.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + permissionTypeNameUpdate + " )");
	}

}
