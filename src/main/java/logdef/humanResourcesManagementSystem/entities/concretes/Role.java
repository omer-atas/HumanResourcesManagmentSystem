package logdef.humanResourcesManagementSystem.entities.concretes;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import logdef.humanResourcesManagementSystem.core.entities.concretes.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","users","roleSuperAdmin"})
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int roleId;
	
	@Column(name="role_name",unique = true)
    @NotEmpty(message = "Role name field cannot be left blank..")
    private String roleName;

    @Column(name="role_definition")
    @NotEmpty(message = "Role definition field cannot be left blank..")
    private String roleDefinition;
    
    @JsonIgnore
    @OneToMany(mappedBy = "role")
	private List<Users> users;
    
    @JsonIgnore
	@OneToMany(mappedBy = "roleSuperAdmin")
	private List<SuperAdmin> roleSuperAdmin;
    
}
