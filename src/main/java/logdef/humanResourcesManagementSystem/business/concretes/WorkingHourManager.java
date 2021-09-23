package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.WorkingHourService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.WorkingHourDao;
import logdef.humanResourcesManagementSystem.entities.concretes.WorkingHour;

@Service
public class WorkingHourManager implements WorkingHourService{
	
	private WorkingHourDao workingHourDao;

	@Autowired
	public WorkingHourManager(WorkingHourDao workingHourDao) {
		super();
		this.workingHourDao = workingHourDao;
	}

	@Override
	public Result add(WorkingHour workingHour) {
		if(this.workingHourDao.getByWorkingHour(workingHour.getWorkingHour()) != null) {
			return new ErrorResult("Bu çalışma saati mevcuttur..");
		}
		
		this.workingHourDao.save(workingHour);
		return new SuccessResult(workingHour.getWorkingHour() + " added..");
	}

	@Override
	public DataResult<List<WorkingHour>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<WorkingHour>>(this.workingHourDao.findAll(pageable).getContent(), "Çalışma saatleri listelendi");
	}

	@Override
	public DataResult<WorkingHour> getByWorkingHour(String workingHour) {
		return new SuccessDataResult<WorkingHour>(this.workingHourDao.getByWorkingHour(workingHour), workingHour + " bulundu..");
	}

	@Override
	public DataResult<WorkingHour> getByWorkingHourId(int workingHourId) {
		return new SuccessDataResult<WorkingHour>(this.workingHourDao.getByWorkingHourId(workingHourId), workingHourId + " bulundu..");
	}

	@Override
	public Result updateByWorkingHour(int workingHourId, String workingHourUpdate) {
		
		if(this.workingHourDao.getByWorkingHourId(workingHourId) ==null){
			return new ErrorResult("Böyle bir çalışma saati kimliği yok.");
		}
		
		this.workingHourDao.updateByWorkingHour(workingHourId, workingHourUpdate);
		
		return new SuccessResult(workingHourId + " updated.. ( " + workingHourUpdate + " )");
	}

	@Override
	public Result deleteByWorkingHourId(int workingHourId) {
		
		if(this.workingHourDao.getByWorkingHourId(workingHourId) ==null){
			return new ErrorResult("Böyle bir çalışma saati kimliği yok.");
		}
		
		this.workingHourDao.deleteByWorkingHourId(workingHourId);
		
		return new SuccessResult(workingHourId + " deleted..");
	}
	
	

}
