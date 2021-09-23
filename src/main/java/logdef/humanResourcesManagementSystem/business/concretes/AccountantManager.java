package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.AccountantService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.AccountantDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Accountant;

@Service
public class AccountantManager implements AccountantService{

	private AccountantDao accountantDao;
	@Autowired	
	public AccountantManager(AccountantDao accountantDao) {
		super();
		this.accountantDao = accountantDao;
	}

	@Override
	public Result add(Accountant accountant) {		
		
		this.accountantDao.save(accountant);
		return new SuccessResult("Accountant added..");
	}

	

	@Override
	public DataResult<List<Accountant>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Accountant>>(this.accountantDao.findAll(pageable).getContent(), "Accountant listed..");
	}

	public boolean checkAccountantId(int employeeId) {
		if(this.accountantDao.getByEmployeeId(employeeId) == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public DataResult<Accountant> getByEmployeeId(int employeeId) {
		if(checkAccountantId(employeeId) == false) {
			return new ErrorDataResult<Accountant>(this.accountantDao.getByEmployeeId(employeeId),
																	"Accountant with this "+employeeId +" not found");
		}
		return new SuccessDataResult<Accountant>(this.accountantDao.getByEmployeeId(employeeId),
					"Accountant : "+ employeeId +" found..");
	
	}

	@Override
	public Result deleteByAccountantId(int employeeId) {
		
		if(checkAccountantId(employeeId) == false) {
			return new ErrorResult("No such accountant was found.");
		}
		
		this.accountantDao.deleteByAccountantId(employeeId);	
		return new SuccessResult("Accountant deleted.. ( " + employeeId + " )");
	}

}
