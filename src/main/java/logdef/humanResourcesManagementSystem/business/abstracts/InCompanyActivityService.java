package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.Date;
import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.InCompanyActivity;

public interface InCompanyActivityService {
	
	Result add(InCompanyActivity inCompanyActivity);
	
	DataResult<List<InCompanyActivity>> getAll(int pageNo, int pageSize);
	
	DataResult<InCompanyActivity> getByEventCalendarId(int eventCalendarId);
	
	DataResult<InCompanyActivity>getByInCompanyActivityName(String inCompanyActivityName);
	
	Result updateByEventCalendarName(int eventCalendarId,String eventCalendarNameUpdate);
	
	Result updateByInCompanyPermissionName(int eventCalendarId,String inCompanyActivityNameUpdate);
	
	Result updateByEndDate(int eventCalendarId,Date endDateUpdate);
	
	Result updateByStartingDate(int eventCalendarId,Date startingDateUpdate);
	
	Result deleteByInCompanyActivityId(int eventCalendarId);

}
