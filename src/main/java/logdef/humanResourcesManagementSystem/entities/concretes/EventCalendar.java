package logdef.humanResourcesManagementSystem.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="event_calendars")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","EmployeeEventCalendarEventCalendar",
	"InternEventCalendarEventCalendar"})
public class EventCalendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="event_calendar_id")
	private int eventCalendarId;
	
	@Column(name="event_calendar_name")
	@NotEmpty(message = "Event calendar name field cannot be left blank..")
	private String eventCalendarName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "EmployeeEventCalendaremployeesManager")
	private List<EmployeeEventCalendar> EmployeeEventCalendarEventCalendar;
	
	@JsonIgnore
	@OneToMany(mappedBy = "InternEventCalendarEventCalendar")
	private List<InternEventCalendar> InternEventCalendarEventCalendar;

}
