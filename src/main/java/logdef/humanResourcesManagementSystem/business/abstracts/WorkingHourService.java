package logdef.humanResourcesManagementSystem.business.abstracts;

import java.util.List;

import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.entities.concretes.WorkingHour;

public interface WorkingHourService {
	
	Result add(WorkingHour workingHour); 
	
	DataResult<List<WorkingHour>> getAll(int pageNo, int pageSize);
	
	DataResult<WorkingHour>  getByWorkingHour(String workingHour);
	
	DataResult<WorkingHour> getByWorkingHourId(int workingHourId);
	
	Result updateByWorkingHour(int workingHourId,String workingHourUpdate);
	
	Result deleteByWorkingHourId(int workingHourId);

}
