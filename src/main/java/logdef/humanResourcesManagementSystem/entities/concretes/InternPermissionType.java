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
@Table(name="intern_permission_types")
@AllArgsConstructor
@NoArgsConstructor
public class InternPermissionType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="intern_permission_type_id")
	private int internPermissionTypeId;
	
	@ManyToOne()
	@JoinColumn(name = "manager_id")
	private Employees InternPermissionTypeEmployeesManager;
	
	@ManyToOne()
	@JoinColumn(name = "permission_type_id")
	private PermissionType InternPermissionTypePermissionType;
	
	@ManyToOne()
	@JoinColumn(name = "intern_id")
	private Intern intern;
}
