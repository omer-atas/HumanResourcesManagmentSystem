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
@Table(name="employee_permission_types")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePermissionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_permission_type_id")
	private int employeePermissionTypeId;
	
	@ManyToOne()
	@JoinColumn(name = "manager_id")
	private Employees employeesManager;
	
	@ManyToOne()
	@JoinColumn(name = "permission_type_id")
	private PermissionType permissionType;
	
	@ManyToOne()
	@JoinColumn(name = "employee_id")
	private Employees employees;	
	
}
