package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.EventCalendarService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EventCalendarDao;
import logdef.humanResourcesManagementSystem.entities.concretes.EventCalendar;

@Service

public class EventCalendarManager implements EventCalendarService{
	
	private EventCalendarDao eventCalendarDao;
	
	@Autowired
	public EventCalendarManager(EventCalendarDao eventCalendarDao) {
		super();
		this.eventCalendarDao = eventCalendarDao;
	}

	@Override
	public Result add(EventCalendar eventCalendar) {
		this.eventCalendarDao.save(eventCalendar);
		return new SuccessResult("Event calendar type of "+ eventCalendar.getEventCalendarName() +" added..");
	}

	@Override
	public DataResult<List<EventCalendar>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<EventCalendar>>(this.eventCalendarDao.findAll(pageable).getContent(), "Event calendars listed...");
	}

	public boolean checkEventCalendarId(int eventCalendarId) {
		
		if(this.eventCalendarDao.getByEventCalendarId(eventCalendarId) == null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public DataResult<EventCalendar> getByEventCalendarId(int eventCalendarId) {
		
		if(checkEventCalendarId(eventCalendarId) == false) {
			return new ErrorDataResult<EventCalendar>(this.eventCalendarDao.
					getByEventCalendarId(eventCalendarId),"Event calendar with this "+ eventCalendarId
					+ " not found");
		}
		
		return new SuccessDataResult<EventCalendar>(this.eventCalendarDao.
				getByEventCalendarId(eventCalendarId), eventCalendarId +" found..");
	}

	@Override
	public Result updateByEventCalendarName(int eventCalendarId, String eventCalendarNameUpdate) {
		
		if(this.eventCalendarDao.getByEventCalendarId(eventCalendarId) == null) {
			return new SuccessResult("No such event calendar was found.");
		}
		
		this.eventCalendarDao.updateByEventCalendarName(eventCalendarId, eventCalendarNameUpdate);
		return new SuccessResult(eventCalendarId + " updated.." + "( " + eventCalendarNameUpdate + " )");
	
	}

	@Override
	public Result deleteByEventCalendarId(int eventCalendarId) {
		
		if(this.eventCalendarDao.getByEventCalendarId(eventCalendarId) == null) {
			return new SuccessResult("No such event calendar was found.");
		}
		
		this.eventCalendarDao.deleteByEventCalendarId(eventCalendarId);
		return new SuccessResult(eventCalendarId + " deleted.." + "( " + eventCalendarId + " )");
	}

}
