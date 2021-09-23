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
@Table(name="official_permissions")

@PrimaryKeyJoinColumn(name="permission_type_id",referencedColumnName = "permission_type_id")
public class OfficialPermission  extends PermissionType {
	
	@Column(name = "permission_type_id")
	private int permissionTypeId;
	
	@Column(name="official_permission_name",unique = true)
	@NotEmpty(message = "Resmi izin alanı buş bırakılamaz....")
	private String officialPermissionName;
	
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
