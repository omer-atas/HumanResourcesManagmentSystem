package logdef.humanResourcesManagementSystem.entities.concretes;



import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data

@Entity
@Table(name="managers")
@PrimaryKeyJoinColumn(name="employee_id",referencedColumnName = "employee_id")

public class Manager extends Employees {

	@Column(name = "employee_id")
	private int employeeId;
	
}
