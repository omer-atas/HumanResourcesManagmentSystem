package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.InternEventCalendar;

public interface InternEventCalendarService {

	Result add(InternEventCalendar internEventCalendar);

	DataResult<List<InternEventCalendar>>getAll(int pageNo, int pageSize);
}
