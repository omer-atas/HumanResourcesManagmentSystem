package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.InCompanyActivityService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InCompanyActivityDao;
import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyActivity;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;

@Service
public class InCompanyActivityManager implements InCompanyActivityService{
	
	private InCompanyActivityDao inCompanyActivityDao;
	
	@Autowired	
	public InCompanyActivityManager(InCompanyActivityDao inCompanyActivityDao) {
		super();
		this.inCompanyActivityDao = inCompanyActivityDao;
	}

	@Override
	public Result add(InCompanyActivity inCompanyActivity) {
		this.inCompanyActivityDao.save(inCompanyActivity);
		return new SuccessResult("Permission type of "+ inCompanyActivity.getInCompanyActivityName() +" added..");
	}

	@Override
	public DataResult<List<InCompanyActivity>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<InCompanyActivity>>(this.inCompanyActivityDao.findAll(pageable).getContent(), "In company activity listed...");
	}

	
	public boolean checkEventCalendarId(int eventCalendarId) {
		
		if(this.inCompanyActivityDao.getByEventCalendarId(eventCalendarId) == null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public DataResult<InCompanyActivity> getByEventCalendarId(int eventCalendarId) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<InCompanyActivity>(this.inCompanyActivityDao.
					getByEventCalendarId(eventCalendarId),"Company activity with this "+ eventCalendarId
					+ " not found");
		}
		
		return new SuccessDataResult<InCompanyActivity>(this.inCompanyActivityDao.
				getByEventCalendarId(eventCalendarId), eventCalendarId +" found..");
	}
	
	public boolean checkInCompanyActivityName(String inCompanyActivityName) {
		
		if(this.inCompanyActivityDao.getByInCompanyActivityName(inCompanyActivityName) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<InCompanyActivity> getByInCompanyActivityName(String inCompanyActivityName) {
		
		if(checkInCompanyActivityName(inCompanyActivityName) == false) {
			return new ErrorDataResult<InCompanyActivity>(this.inCompanyActivityDao.
					getByInCompanyActivityName(inCompanyActivityName),"Company activityNamename with this "+ inCompanyActivityName
					+" not found");
		}
		
		return new SuccessDataResult<InCompanyActivity>(this.inCompanyActivityDao.
				getByInCompanyActivityName(inCompanyActivityName), inCompanyActivityName +" found..");
	}

	@Override
	public Result updateByEventCalendarName(int eventCalendarId, String eventCalendarNameUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such company activity was found.");
		}
		
		this.inCompanyActivityDao.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + eventCalendarNameUpdate + " )");
	}

	@Override
	public Result updateByInCompanyPermissionName(int eventCalendarId, String inCompanyActivityNameUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such company activity was found.");
		}
		
		this.inCompanyActivityDao.updateByInCompanyPermissionName(eventCalendarId, inCompanyActivityNameUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + inCompanyActivityNameUpdate + " )");
	}

	@Override
	public Result updateByEndDate(int eventCalendarId, Date endDateUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such company activity was found.");
		}
		
		this.inCompanyActivityDao.updateByEndDate(eventCalendarId, endDateUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + endDateUpdate + " )");
	}

	@Override
	public Result updateByStartingDate(int eventCalendarId, Date startingDateUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such company activity was found.");
		}
		
		this.inCompanyActivityDao.updateByStartingDate(eventCalendarId, startingDateUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + startingDateUpdate + " )");
	}

	@Override
	public Result deleteByInCompanyActivityId(int eventCalendarId) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such company activity was found.");
		}
		
		this.inCompanyActivityDao.deleteByInCompanyActivityId(eventCalendarId);
		return new SuccessResult(eventCalendarId + " deleted..");
	}

}
