package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.ReligiousHolidaysService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ReligiousHolidaysDao;
import logdef.humanResourcesManagementSystem.entities.concretes.Intern;
import logdef.humanResourcesManagementSystem.entities.concretes.ReligiousHoliday;

@Service
public class ReligiousHolidayManager implements ReligiousHolidaysService{
	
	private ReligiousHolidaysDao religiousHolidaysDao;

	@Autowired
	public ReligiousHolidayManager(ReligiousHolidaysDao religiousHolidaysDao) {
		super();
		this.religiousHolidaysDao = religiousHolidaysDao;
	}

	@Override
	public Result add(ReligiousHoliday religiousHoliday) {
		this.religiousHolidaysDao.save(religiousHoliday);
		return new SuccessResult("Religious holiday type of "+ religiousHoliday.getEventCalendarName() +" added..");
	}

	@Override
	public DataResult<List<ReligiousHoliday>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);		
		return new SuccessDataResult<List<ReligiousHoliday>>(this.religiousHolidaysDao.findAll(pageable).getContent(), "Religious holidays listed...");
	}
	
	public boolean checkEventCalendarId(int eventCalendarId) {
		
		if(this.religiousHolidaysDao.getByEventCalendarId(eventCalendarId) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<ReligiousHoliday> getByEventCalendarId(int eventCalendarId) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<ReligiousHoliday>(this.religiousHolidaysDao.
					getByEventCalendarId(eventCalendarId),"Religious holiday with this "+ eventCalendarId
					+ " not found");
		}
		
		return new SuccessDataResult<ReligiousHoliday>(this.religiousHolidaysDao.
				getByEventCalendarId(eventCalendarId), eventCalendarId +" found..");
	}
	
	public boolean checkReligiouslHolidayName(String religiouslHolidayName) {
		
		if(this.religiousHolidaysDao.getByReligiouslHolidayName(religiouslHolidayName) == null) {
			return false;
		}
		
		return true;
	}


	@Override
	public DataResult<ReligiousHoliday> getByReligiouslHolidayName(String religiouslHolidayName) {
		
		if(checkReligiouslHolidayName(religiouslHolidayName) == false) {
			return new ErrorDataResult<ReligiousHoliday>(this.religiousHolidaysDao.
					getByReligiouslHolidayName(religiouslHolidayName),"Religious holiday name with this "+ religiouslHolidayName
					+" not found");
		}
		
		return new SuccessDataResult<ReligiousHoliday>(this.religiousHolidaysDao.
				getByReligiouslHolidayName(religiouslHolidayName), religiouslHolidayName +" found..");
	}

	@Override
	public Result updateByEventCalendarName(int eventCalendarId, String eventCalendarNameUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such religious holiday was found.");
		}
		
		this.religiousHolidaysDao.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + eventCalendarNameUpdate + " )");
	}

	@Override
	public Result updateByReligiouslHolidayName(int eventCalendarId, String religiouslHolidayNameUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such religious holiday was found.");
		}
		
		this.religiousHolidaysDao.updateByReligiouslHolidayName(eventCalendarId, religiouslHolidayNameUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + religiouslHolidayNameUpdate + " )");
	}

	@Override
	public Result updateByStartingDate(int eventCalendarId, Date startingDateUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such religious holiday was found.");
		}
		
		this.religiousHolidaysDao.updateByStartingDate(eventCalendarId, startingDateUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + startingDateUpdate + " )");
	}

	@Override
	public Result updateByEndDate(int eventCalendarId, Date endDateUpdate) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such religious holiday was found.");
		}
		
		this.religiousHolidaysDao.updateByEndDate(eventCalendarId, endDateUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + endDateUpdate + " )");
	}

	@Override
	public Result deleteByReligiousHolidayId(int eventCalendarId) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<Intern>("No such religious holiday was found.");
		}
		
		this.religiousHolidaysDao.deleteByReligiousHolidayId(eventCalendarId);
		return new SuccessResult(eventCalendarId + " updated..");
	}

	
	
	

}
