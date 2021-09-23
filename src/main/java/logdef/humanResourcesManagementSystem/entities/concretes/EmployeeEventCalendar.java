package logdef.humanResourcesManagementSystem.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="employee_event_calendars")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEventCalendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_event_calendar_id")
	private int employeeEventCalendarId;
	
	@ManyToOne()
	@JoinColumn(name = "manager_id")
	private Employees EmployeeEventCalendaremployeesManager;
	
	@ManyToOne()
	@JoinColumn(name = "event_calender_id")
	private EventCalendar EmployeeEventCalendarEventCalendar;
	
	@ManyToOne()
	@JoinColumn(name = "employee_id")
	private Employees EmployeeEventCalendarEmployees;

}
