package logdef.humanResourcesManagementSystem.entities.concretes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import logdef.humanResourcesManagementSystem.core.entities.concretes.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler",
	"employeePermissionTypeManager","employeePermissionType",
	"EmployeeEventCalendaremployeesManager","EmployeeEventCalendarEmployees",
	"InternEventCalendarEmployeesManager","generalInformation","employeesUser","socialMedia"})

public class Employees{	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
	private int employeeId;	
	
	@Column(name="first_name")
	@NotEmpty(message = "Ad alanı boş bırakılamaz..")
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty(message = "Soyad alanı boş bırakılamaz..")
	private String lastName;
	
	@Column(name="identity_number",length = 11, unique = true)
	@NotEmpty(message = "Tc kimlik numarası alanı boş bırakılamaz..")
	@Size(min = 11 , max=11 , message = "Tc kimlik numarası 11 haneli olmalıdır..")
	private String identityNumber;
	
	@Column(name="nationality")
	@NotEmpty(message = "Uyruk alanı boş bırakılamaz..")
	private String nationality;
	
	@Column(name="blood_group")
	@NotEmpty(message = "Kan grubu alanı boş bırakılamaz..")
	private String bloodGroup;
	
	@Column(name="birthday")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
	
	@Column(name="gender")
	@NotEmpty(message = "Cinsiyet alanı boş bırakılamaz..")
	private String gender;
	
	@Column(name="marital_status")
	@NotEmpty(message = "Evlilik durumu alanı boş bırakılamaz..")
	private String maritalStatus;
	
	@Column(name="military_status")
	private String militaryStatus;
	
	@Column(name="children_count")
	private int childrenCount;
	
	@JsonIgnore
	@Column(name="create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01 00:00:00")
	private LocalDateTime  createDate  = LocalDateTime.now();
	
	@JsonIgnore
	@OneToOne(
			fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "employeesUser")
	private Users employeesUser;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.MERGE,
            mappedBy = "employeesSocialMedia")
	private SocialMedia socialMedia;

	@JsonIgnore
	@OneToOne(
			fetch = FetchType.LAZY,
	        cascade =  CascadeType.ALL,
	        mappedBy = "employeesGeneralInformation")
	private GeneralInformation generalInformation;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employeesManager")
	private List<EmployeePermissionType> employeePermissionTypeManager;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employees")
	private List<EmployeePermissionType> employeePermissionType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "EmployeeEventCalendaremployeesManager")
	private List<EmployeeEventCalendar> EmployeeEventCalendaremployeesManager;
	
	@JsonIgnore
	@OneToMany(mappedBy = "EmployeeEventCalendarEmployees")
	private List<EmployeeEventCalendar> EmployeeEventCalendarEmployees;
	
	@JsonIgnore
	@OneToMany(mappedBy = "InternPermissionTypeEmployeesManager")
	private List<InternPermissionType> InternPermissionTypeEmployeesManager;
	
	@JsonIgnore
	@OneToMany(mappedBy = "InternEventCalendarEmployeesManager")
	private List<InternEventCalendar> InternEventCalendarEmployeesManager;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name	=	"employess_authorities",
			joinColumns	=	@JoinColumn(name="employee_id"),
			inverseJoinColumns	=	@JoinColumn(name="authority_id")
			)
	private List<Authority> authorities;
	
}
