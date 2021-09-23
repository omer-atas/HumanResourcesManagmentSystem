package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.OfficialHolidayService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.OfficialHolidayDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialHoliday;

@Service
public class OfficialHolidayManager implements OfficialHolidayService {
	
	private OfficialHolidayDao officialHolidayDao;

	@Autowired
	public OfficialHolidayManager(OfficialHolidayDao officialHolidayDao) {
		super();
		this.officialHolidayDao = officialHolidayDao;
	}

	public boolean checkOfficialHolidayName(String officialHolidayName) {
		
		if(this.officialHolidayDao.getByOfficialHolidayName(officialHolidayName) == null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public Result add(OfficialHoliday OfficialHoliday) {
		
		if(checkOfficialHolidayName(OfficialHoliday.getOfficialHolidayName()) == true) {
			return new ErrorResult("There is someone with this official holiday name..");
		}
		
		this.officialHolidayDao.save(OfficialHoliday);
		return new SuccessResult("Official permission of "+ OfficialHoliday.getOfficialHolidayName() +" added..");
	}

	@Override
	public DataResult<List<OfficialHoliday>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<OfficialHoliday>>(this.officialHolidayDao.findAll(pageable).getContent(), "Official holiday listed...");
	}
	
	public boolean checkEventCalendarId(int eventCalendarId) {
		
		if(this.officialHolidayDao.getByEventCalendarId(eventCalendarId) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<OfficialHoliday> getByEventCalendarId(int eventCalendarId) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<OfficialHoliday>(this.officialHolidayDao.
					getByEventCalendarId(eventCalendarId),"Official holiday with this "+ eventCalendarId
					+ " not found");
		}
		
		return new SuccessDataResult<OfficialHoliday>(this.officialHolidayDao.
				getByEventCalendarId(eventCalendarId), eventCalendarId +" found..");
	}
	

	@Override
	public DataResult<OfficialHoliday> getByOfficialHolidayName(String officialHolidayName) {
		
		if(checkOfficialHolidayName(officialHolidayName) == false) {
			return new ErrorDataResult<OfficialHoliday>(this.officialHolidayDao.
					getByOfficialHolidayName(officialHolidayName),"Official permission with this "+ officialHolidayName
					+ " not found");
		}
		
		return new SuccessDataResult<OfficialHoliday>(this.officialHolidayDao.
				getByOfficialHolidayName(officialHolidayName), officialHolidayName +" found..");
	}

	@Override
	public Result updateByEventCalendarName(int eventCalendarId, String eventCalendarNameUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such official holiday was found.");
		}
		
		this.officialHolidayDao.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + eventCalendarNameUpdate + " )");
	}

	@Override
	public Result updateByOfficialHolidayName(int eventCalendarId, String officialHolidayNameUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such official holiday was found.");
		}
		
		this.officialHolidayDao.updateByOfficialHolidayName(eventCalendarId, officialHolidayNameUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + officialHolidayNameUpdate + " )");
	}

	@Override
	public Result updateByStartingDate(int eventCalendarId, Date startingDateUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such official holiday was found.");
		}
		
		this.officialHolidayDao.updateByStartingDate(eventCalendarId, startingDateUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + startingDateUpdate + " )");
	}

	@Override
	public Result updateByEndDate(int eventCalendarId, Date endDateUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such official holiday was found.");
		}
		
		this.officialHolidayDao.updateByEndDate(eventCalendarId, endDateUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + endDateUpdate + " )");
	}

	@Override
	public Result deleteByOfficialHolidayId(int eventCalendarId) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such official holiday was found.");
		}
		
		this.officialHolidayDao.deleteByOfficialHolidayId(eventCalendarId);
		return new SuccessResult(eventCalendarId + " updated.." );
	}
	
	

}
