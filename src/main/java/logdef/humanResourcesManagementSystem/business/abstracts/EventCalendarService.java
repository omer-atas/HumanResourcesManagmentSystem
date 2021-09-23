package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.EventCalendar;

public interface EventCalendarService {
	
	Result add(EventCalendar eventCalendar);
	
	DataResult<List<EventCalendar>> getAll(int pageNo, int pageSize);
	
	DataResult<EventCalendar> getByEventCalendarId(int eventCalendarId);
	
	Result updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);
	
	Result deleteByEventCalendarId(int eventCalendarId);

}
