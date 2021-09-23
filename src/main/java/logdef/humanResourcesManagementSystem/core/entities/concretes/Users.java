package logdef.humanResourcesManagementSystem.core.entities.concretes;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import logdef.humanResourcesManagementSystem.entities.concretes.Employees;
import logdef.humanResourcesManagementSystem.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employees","role"})
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="email",unique = true)
	@Email
	@NotBlank
	@NotNull
	private String email;
	
	@Column(name="password")
	@NotBlank
	@NotNull
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_id",nullable = true)
    private Employees employeesUser;
	
	@ManyToOne()
	@JoinColumn(name = "role_id")
	private Role role;
}
