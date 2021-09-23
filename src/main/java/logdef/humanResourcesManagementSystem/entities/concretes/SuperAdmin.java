package logdef.humanResourcesManagementSystem.entities.concretes;


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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="super_admin")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","authorities"})
public class SuperAdmin  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="super_admin_id")
	private int superAdminId;
	
	@Column(name="first_name")
	@NotEmpty(message = "First name field cannot be left blank..")
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty(message = "Last name field cannot be left blank..")
	private String lastName;
	
	@Column(name="email",unique = true)
    @NotEmpty(message = "Email field cannot be left blank..")
    @Email
    private String email;
	
	@Column(name="password")
	@NotEmpty(message = "Password field cannot be left blank..")
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "role_id")
	private Role roleSuperAdmin;
	
	@JsonIgnore
	@OneToMany(mappedBy = "superAdmin")
	private List<Authority> authorities;

}
