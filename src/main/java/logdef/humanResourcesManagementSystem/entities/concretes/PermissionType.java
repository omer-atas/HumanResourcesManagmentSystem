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
@Table(name="permission_types")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employeePermissionType",
	"InternPermissionTypePermissionType"})
public class PermissionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="permission_type_id")
	private int permissionTypeId;
	
	@Column(name="permission_type_name")
	@NotEmpty(message = "Permission type name field cannot be left blank..")
	private String permissionTypeName;

	@JsonIgnore
	@OneToMany(mappedBy = "permissionType")
	private List<EmployeePermissionType> employeePermissionType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "InternPermissionTypePermissionType")
	private List<InternPermissionType> InternPermissionTypePermissionType;
	

}
