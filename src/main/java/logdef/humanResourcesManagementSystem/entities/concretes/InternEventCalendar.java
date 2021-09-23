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
@Table(name="intern_event_calendars")
@AllArgsConstructor
@NoArgsConstructor
public class InternEventCalendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="intern_event_calendar_id")
	private int internEventCalendarId;
	
	@ManyToOne()
	@JoinColumn(name = "manager_id")
	private Employees InternEventCalendarEmployeesManager;
	
	@ManyToOne()
	@JoinColumn(name = "event_calendar_id")
	private EventCalendar InternEventCalendarEventCalendar;
	
	@ManyToOne()
	@JoinColumn(name = "intern_id")
	private Intern InternEventCalendarIntern;

}
