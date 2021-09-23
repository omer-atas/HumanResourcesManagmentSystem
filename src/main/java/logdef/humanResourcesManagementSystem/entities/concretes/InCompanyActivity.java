package logdef.humanResourcesManagementSystem.entities.concretes;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data

@Entity
@Table(name="in_company_activity")

@PrimaryKeyJoinColumn(name="event_calendar_id",referencedColumnName = "event_calendar_id")
public class InCompanyActivity extends EventCalendar{
	
	@Column(name = "event_calendar_id")
	private int eventCalendarId;
	
	@Column(name="in_company_activity_name")
	@NotEmpty(message = "Şirket içi etkinlik alanı boş bırakılamaz..")
	private String inCompanyActivityName;
	
	@Column(name="starting_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startingDate;
	 
	@Column(name="endDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;
	
	@JsonIgnore
	@Column(name="create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01 00:00:00")
	private LocalDateTime  createDate  = LocalDateTime.now();

}
