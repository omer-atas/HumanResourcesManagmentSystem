package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import logdef.humanResourcesManagementSystem.entities.concretes.EmployeeEventCalendar;

public interface EmployeeEventCalendarDao extends JpaRepository<EmployeeEventCalendar, Integer> {
	
	EmployeeEventCalendar getByEmployeeEventCalendarId(int employeeEventCalendarId);

}
