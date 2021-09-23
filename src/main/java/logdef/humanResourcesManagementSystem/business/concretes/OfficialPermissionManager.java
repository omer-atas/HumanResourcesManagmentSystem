package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.OfficialPermissionService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.OfficialPermissionDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialPermission;
@Service
public class OfficialPermissionManager implements OfficialPermissionService {
	
	private OfficialPermissionDao officialPermissionDao;
	
	
	@Autowired
	public OfficialPermissionManager(OfficialPermissionDao officialPermissionDao) {
		super();
		this.officialPermissionDao = officialPermissionDao;
	}

	@Override
	public Result add(OfficialPermission officialPermission) {
		
		if(checkOfficialPermissionName(officialPermission.getOfficialPermissionName()) == true) {
			return new ErrorResult("There is someone with this official permission name..");
		}
		
		this.officialPermissionDao.save(officialPermission);
		return new SuccessResult("Official permission of "+ officialPermission.getOfficialPermissionName() +" added..");
	}
	
	public boolean checkOfficialPermissionName(String officialPermissionName) {
		
		if(this.officialPermissionDao.getByOfficialPermissionName(officialPermissionName) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<List<OfficialPermission>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<OfficialPermission>>(this.officialPermissionDao.findAll(pageable).getContent(), "Official permission listed...");
	}

	public boolean checkPermissionTypeId(int permissionTypeId) {
		
		if(this.officialPermissionDao.getByPermissionTypeId(permissionTypeId) == null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public DataResult<OfficialPermission> getByPermissionTypeId(int permissionTypeId) {
		
		if(checkPermissionTypeId(permissionTypeId) == false) {
			return new ErrorDataResult<OfficialPermission>(this.officialPermissionDao.
					getByPermissionTypeId(permissionTypeId),"Official permission with this "+ permissionTypeId
					+ " not found");
		}
		
		return new SuccessDataResult<OfficialPermission>(this.officialPermissionDao.
				getByPermissionTypeId(permissionTypeId), permissionTypeId +" found..");
	}

	@Override
	public DataResult<OfficialPermission> getByOfficialPermissionName(String officialPermissionName) {
		
		if(checkOfficialPermissionName(officialPermissionName) == false) {
			return new ErrorDataResult<OfficialPermission>(this.officialPermissionDao.
					getByOfficialPermissionName(officialPermissionName),"Official permission with this "+ officialPermissionName
					+ " not found");
		}
		
		return new SuccessDataResult<OfficialPermission>(this.officialPermissionDao.
				getByOfficialPermissionName(officialPermissionName), officialPermissionName +" found..");
	}

	@Override
	public Result updateByOfficialPermissionName(int permissionTypeId, String officialPermissionNameUpdate) {
		
		if(checkPermissionTypeId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such official permission was found.");
		}
		
		this.officialPermissionDao.updateByOfficialPermissionName(permissionTypeId, officialPermissionNameUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + officialPermissionNameUpdate + " )");
	}

	@Override
	public Result updateByStartingDate(int permissionTypeId, Date startingDateUpdate) {
		
		if(checkPermissionTypeId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such official permission was found.");
		}
		
		this.officialPermissionDao.updateByStartingDate(permissionTypeId, startingDateUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + startingDateUpdate + " )");
	}

	@Override
	public Result updateByEndDate(int permissionTypeId, Date endDateUpdate) {
		
		if(checkPermissionTypeId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such official permission was found.");
		}
		
		this.officialPermissionDao.updateByEndDate(permissionTypeId, endDateUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + endDateUpdate + " )");
	}

	@Override
	public Result deleteByOfficialPermissionId(int permissionTypeId) {
		
		if(checkPermissionTypeId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such official permission was found.");
		}
		
		this.officialPermissionDao.deleteByOfficialPermissionId(permissionTypeId);
		return new SuccessResult(permissionTypeId + " deleted..");
	}

	@Override
	public Result updateByPermissionTypeName(int permissionTypeId, String permissionTypeNameUpdate) {
		
		if(checkPermissionTypeId(permissionTypeId) == false) {
			return new ErrorDataResult<Intern>("No such official permission was found.");
		}
		
		this.officialPermissionDao.updateByPermissionTypeName(permissionTypeId, permissionTypeNameUpdate);
		return new SuccessResult(permissionTypeId + " updated.." + "( " + permissionTypeNameUpdate + " )");
	}

}
