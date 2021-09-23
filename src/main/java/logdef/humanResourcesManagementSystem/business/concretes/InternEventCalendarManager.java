package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.InternEventCalendarService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EventCalendarDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InternDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.InternEventCalendarDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ManagerDao;
import logdef.humanResourcesManagementSystem.entities.concretes.InternEventCalendar;

@Service
public class InternEventCalendarManager implements InternEventCalendarService{
	
	private InternEventCalendarDao internEventCalendarDao;
	private ManagerDao managerDao;
	private InternDao internDao;
	private EventCalendarDao eventCalendarDao;

	@Autowired
	public InternEventCalendarManager(InternEventCalendarDao internEventCalendarDao, ManagerDao managerDao,
			InternDao internDao, EventCalendarDao eventCalendarDao) {
		super();
		this.internEventCalendarDao = internEventCalendarDao;
		this.managerDao = managerDao;
		this.internDao = internDao;
		this.eventCalendarDao = eventCalendarDao;
	}


	@Override
	public Result add(InternEventCalendar internEventCalendar) {
		
		if(internEventCalendar.getInternEventCalendarEmployeesManager().getEmployeeId() == 0 ||
				internEventCalendar.getInternEventCalendarEventCalendar().getEventCalendarId() == 0 ||
						internEventCalendar.getInternEventCalendarIntern().getInternId() == 0) {
			return new ErrorResult("Null bırakılamaz..");
		}
		
		if((this.managerDao.getByEmployeeId(internEventCalendar.getInternEventCalendarEmployeesManager().getEmployeeId()) == null)
				|| (this.internDao.getByInternId(internEventCalendar.getInternEventCalendarIntern().getInternId()) == null)
				|| (this.eventCalendarDao.getByEventCalendarId(internEventCalendar.getInternEventCalendarEventCalendar().getEventCalendarId()) == null)) {
			return new ErrorResult("Boş değer girmeyiniz..");
		}
		
		if(this.managerDao.getByEmployeeId(internEventCalendar.getInternEventCalendarEmployeesManager().getEmployeeId()) == null) {
			return new ErrorResult("Yönetici giriniz....");
		}
		
		this.internEventCalendarDao.save(internEventCalendar);
		return new SuccessResult("Intern event calendar "+ internEventCalendar.getInternEventCalendarId() +" added..");
	}

	@Override
	public DataResult<List<InternEventCalendar>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<InternEventCalendar>>(this.internEventCalendarDao.findAll(pageable).getContent(), "Intern event calendars listed...");
	}
	
	

}
