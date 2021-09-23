package logdef.humanResourcesManagementSystem.entities.concretes;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data

@Entity
@Table(name="employess_authorities")
@AllArgsConstructor
@NoArgsConstructor
public class EmployessAuthorities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_authority_id")
	private int employeeAuthorityId;
	
	@Column(name="employee_id")
	@NotEmpty(message = "Employee id field cannot be left blank..")
	private int employeeId;
	
	@Column(name="authority_id")
	@NotEmpty(message = "Authority id field cannot be left blank..")
	private int authorityId;
	
	@Column(name="in_company_permission_control")
	private boolean inCompanyPermissionControl;
	
	@Column(name="in_company_permission_name")
	private String inCompanyPermissionName;
	
	@JsonIgnore
	@Column(name="create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01 00:00:00")
	private LocalDateTime  createDate  = LocalDateTime.now();

}
