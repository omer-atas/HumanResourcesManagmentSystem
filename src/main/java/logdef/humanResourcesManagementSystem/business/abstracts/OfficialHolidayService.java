package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;
import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.OfficialHoliday;

public interface OfficialHolidayService {
	
	Result add(OfficialHoliday OfficialHoliday);
	
	DataResult<List<OfficialHoliday>> getAll(int pageNo, int pageSize);
	
	DataResult<OfficialHoliday> getByEventCalendarId(int eventCalendarId);
	
	DataResult<OfficialHoliday> getByOfficialHolidayName(String officialHolidayName);
	
	Result updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);
	
	Result updateByOfficialHolidayName(int eventCalendarId,String officialHolidayNameUpdate);
	
	Result updateByStartingDate(int eventCalendarId,Date startingDateUpdate);
	
	Result updateByEndDate(int eventCalendarId,Date endDateUpdate);
	
	Result deleteByOfficialHolidayId(int eventCalendarId);

}
