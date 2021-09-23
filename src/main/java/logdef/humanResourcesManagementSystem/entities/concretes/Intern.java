package logdef.humanResourcesManagementSystem.entities.concretes;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="intern")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","intern","InternEventCalendarIntern","projectIntern"})
public class Intern {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="intern_id")
	private int internId;
	
	@Column(name="first_name")
	@NotEmpty(message = "Name field cannot be left blank..")
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty(message = "Surname field cannot be left blank..")
	private String lastName;
	
	@Column(name="email")
    @NotEmpty(message = "Email field cannot be left blank..")
    @Email
    private String email;
	
	@Column(name="password")
	@NotEmpty(message = "Password field cannot be left blank..")
	private String password;
	
	@Column(name="address")
	@NotEmpty(message = "address field cannot be left blank..")
	private String address;
	
	@Column(name="telephone_number")
	@NotEmpty(message = "Telephone number field cannot be left blank..")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "(222) 222-2222")
	private String telephoneNumber;
	
	@Column(name="internship_started_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date internshipStartedDate;
	
	@Column(name="internship_end_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date internshipEndDate;
	
	@ManyToOne()
	@JoinColumn(name = "studied_department_id")
	private StudiedDepartment studiedDepartmentIntern;
	
	@ManyToOne()
	@JoinColumn(name = "studied_section_id")
	private StudiedSection studiedSectionIntern;
	
	@JsonIgnore
	@OneToMany(mappedBy = "intern")
	private List<InternPermissionType> intern;
	
	@JsonIgnore
	@OneToMany(mappedBy = "InternEventCalendarIntern")
	private List<InternEventCalendar> InternEventCalendarIntern;
	
	@JsonIgnore
	@OneToMany(mappedBy = "projectIntern")
	private List<ProjectIntern> projectIntern;

}
