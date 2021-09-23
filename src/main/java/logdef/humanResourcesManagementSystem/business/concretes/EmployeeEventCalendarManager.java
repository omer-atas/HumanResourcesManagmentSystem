package logdef.humanResourcesManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import logdef.humanResourcesManagementSystem.business.abstracts.EmployeeEventCalendarService;
import logdef.humanResourcesManagementSystem.core.utilities.results.DataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.ErrorResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.Result;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessDataResult;
import logdef.humanResourcesManagementSystem.core.utilities.results.SuccessResult;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeeEventCalendarDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EmployeesDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.EventCalendarDao;
import logdef.humanResourcesManagementSystem.dataAccess.abstracts.ManagerDao;
import logdef.humanResourcesManagementSystem.entities.concretes.EmployeeEventCalendar;

@Service
public class EmployeeEventCalendarManager implements EmployeeEventCalendarService {
	
	private EmployeeEventCalendarDao employeeEventCalendarDao;
	private ManagerDao managerDao;
	private EmployeesDao employeesDao;
	private EventCalendarDao eventCalendarDao;

	@Autowired
	public EmployeeEventCalendarManager(EmployeeEventCalendarDao employeeEventCalendarDao,ManagerDao managerDao,EmployeesDao employeesDao,EventCalendarDao eventCalendarDao) {
		super();
		this.employeeEventCalendarDao = employeeEventCalendarDao;
		this.managerDao = managerDao;
		this.employeesDao = employeesDao;
		this.eventCalendarDao = eventCalendarDao;
	}

	@Override
	public Result add(EmployeeEventCalendar employeeEventCalendar) {
		
		if(employeeEventCalendar.getEmployeeEventCalendarEmployees().getEmployeeId() == 0 ||
				employeeEventCalendar.getEmployeeEventCalendaremployeesManager().getEmployeeId() == 0 ||
						employeeEventCalendar.getEmployeeEventCalendarEventCalendar().getEventCalendarId() == 0) {
			return new ErrorResult("Null bırakılamaz..");
		}
		
		if((this.employeesDao.getByEmployeeId(employeeEventCalendar.getEmployeeEventCalendarEmployees().getEmployeeId()) == null)
				|| (this.managerDao.getByEmployeeId(employeeEventCalendar.getEmployeeEventCalendaremployeesManager().getEmployeeId()) == null)
				|| (this.eventCalendarDao.getByEventCalendarId(employeeEventCalendar.getEmployeeEventCalendarEventCalendar().getEventCalendarId()) == null)) {
			return new ErrorResult("Boş değer girmeyiniz..");
		}
		
		if(this.managerDao.getByEmployeeId(employeeEventCalendar.getEmployeeEventCalendaremployeesManager().getEmployeeId()) == null) {
			return new ErrorResult("Yönetici giriniz....");
		}
		
		this.employeeEventCalendarDao.save(employeeEventCalendar);
		return new SuccessResult("Employee event calendar "+ employeeEventCalendar.getEmployeeEventCalendarId()  +" added..");
	}

	@Override
	public DataResult<List<EmployeeEventCalendar>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return new SuccessDataResult<List<EmployeeEventCalendar>>(this.employeeEventCalendarDao.findAll(pageable).getContent(), "Employee event calendars listed...");
	}
	
	

}
