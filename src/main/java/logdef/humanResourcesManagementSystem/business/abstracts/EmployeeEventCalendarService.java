package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployeeEventCalendar;

public interface EmployeeEventCalendarService {
	
	Result add(EmployeeEventCalendar employeeEventCalendar);
	
	DataResult<List<EmployeeEventCalendar>> getAll(int pageNo, int pageSize);

}
