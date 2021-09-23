package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.AuthorityService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.AuthorityDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Authority;

@Service
public class AuthorityManager implements AuthorityService{

	private AuthorityDao authorityDao;

	@Autowired
	public AuthorityManager(AuthorityDao authorityDao) {
		super();
		this.authorityDao = authorityDao;
	}

	@Override
	public Result add(Authority authority) {
		  
		
		this.authorityDao.save(authority);
		return new SuccessResult(authority.getAuthorityId() + " added..");
	}

	@Override
	public DataResult<List<Authority>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return new SuccessDataResult<List<Authority>>(this.authorityDao.findAll(pageable).getContent(),"Authority listed");
	}

	@Override
	public Authority getByAuthorityId(int authorityId) {
		
		return this.authorityDao.getByAuthorityId(authorityId);
	}
	
	
	
	
}
