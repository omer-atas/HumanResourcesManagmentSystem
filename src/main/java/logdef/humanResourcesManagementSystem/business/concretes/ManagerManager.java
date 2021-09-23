package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.ManagerService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ManagerDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Manager;

@Service
public class ManagerManager implements ManagerService {

	private ManagerDao managerDao;
	
	@Autowired
	public ManagerManager(ManagerDao managerDao) {
		super();
		this.managerDao = managerDao;
	}

	@Override
	public Result add(Manager manager) {
		
		this.managerDao.save(manager);
		return new SuccessResult("Manager added..");
	}
	

	@Override
	public DataResult<List<Manager>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Manager>>(this.managerDao.findAll(pageable).getContent(), "Manager listed..");
	}
	
	public boolean checkManagerId(int employeeId) {
		if(this.managerDao.getByEmployeeId(employeeId) == null) {
			return false;
		}
		return true;
	}

	@Override
	public DataResult<Manager> getByEmployeeId(int employeeId) {
		
		if(checkManagerId(employeeId) == false) {
			return new ErrorDataResult<Manager>(this.managerDao.getByEmployeeId(employeeId),
																	"Manager with this "+employeeId +" not found");
		}
		return new SuccessDataResult<Manager>(this.managerDao.getByEmployeeId(employeeId),
					"Manager : "+ employeeId +" found..");
	}

	@Override
	public Result deleteByAccountantId(int employeeId) {
		
		if(checkManagerId(employeeId) == false) {
			return new ErrorResult("No such manager was found.");
		}
		
		this.managerDao.deleteByAccountantId(employeeId);	
		return new SuccessResult("Manager deleted.. (" + employeeId + " )");
	}
	
	
	
	
}
