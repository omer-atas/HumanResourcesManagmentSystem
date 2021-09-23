package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.EmployessAuthoritiesService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployessAuthoritiesDao;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployessAuthorities;

@Service
public class EmployessAuthoritiesManager implements EmployessAuthoritiesService{

	private EmployessAuthoritiesDao employessAuthoritiesDao;

	@Autowired
	public EmployessAuthoritiesManager(EmployessAuthoritiesDao employessAuthoritiesDao) {
		super();
		this.employessAuthoritiesDao = employessAuthoritiesDao;
	}

	@Override
	public Result add(EmployessAuthorities employessAuthorities) {

		this.employessAuthoritiesDao.save(employessAuthorities);
		return new SuccessResult("EmployessAuthorities added.");
	}

	@Override
	public DataResult<List<EmployessAuthorities>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return new SuccessDataResult<List<EmployessAuthorities>>(this.employessAuthoritiesDao.findAll(pageable).getContent(), "EmployessAuthorities listed");
	}
	
	
	
}
