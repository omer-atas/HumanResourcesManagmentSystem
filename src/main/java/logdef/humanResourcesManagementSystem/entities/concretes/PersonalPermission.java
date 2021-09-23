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
@Table(name="personal_permissions")
@PrimaryKeyJoinColumn(name="permission_type_id",referencedColumnName = "permission_type_id")

public class PersonalPermission extends PermissionType {
	
	@Column(name = "permission_type_id")
	private int permissionTypeId;
	
	@Column(name="personal_permission_name")
	@NotEmpty(message = "Personal permission name field cannot be left blank..")
	private String personalPermissionName;
	
	@Column(name="starting_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotEmpty(message = "Starting date field cannot be left blank..")
	private Date startingDate;
	 
	@Column(name="endDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotEmpty(message = "End date field cannot be left blank..")
	private Date endDate;
	 
	@JsonIgnore
	@Column(name="create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01 00:00:00")
	private LocalDateTime  createDate  = LocalDateTime.now();

}
