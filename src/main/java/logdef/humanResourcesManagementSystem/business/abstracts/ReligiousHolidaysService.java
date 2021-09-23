package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;
import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.ReligiousHoliday;

public interface ReligiousHolidaysService {
	
	Result add(ReligiousHoliday religiousHoliday);
	
	DataResult<List<ReligiousHoliday>> getAll(int pageNo, int pageSize);
	
	DataResult<ReligiousHoliday> getByEventCalendarId(int eventCalendarId);
	
	DataResult<ReligiousHoliday> getByReligiouslHolidayName(String religiouslHolidayName);
	
	Result updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);
	
	Result updateByReligiouslHolidayName(int eventCalendarId,String religiouslHolidayNameUpdate);
	
	Result updateByStartingDate(int eventCalendarId,Date startingDateUpdate);
	
	Result updateByEndDate(int eventCalendarId,Date endDateUpdate);
	
	Result deleteByReligiousHolidayId(int eventCalendarId);

}
