package logdef.humanResourcesManagementSystem.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="authorities")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employessAuthority"})
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="authority_id")
	private int authorityId;

	
	@Column(name="in_company_permission_control")
	private boolean inCompanyPermissionControl;
	
	@ManyToOne()
	@JoinColumn(name = "super_admin_id")
	private SuperAdmin superAdmin;
	
	@ManyToMany(
			mappedBy="authorities",
			cascade = {
					    CascadeType.PERSIST, 
					    CascadeType.MERGE
					    }
			)
	private List<Employees> employessAuthority;
	
	public boolean getInCompanyPermissionControl() {
		    return inCompanyPermissionControl;
	}
	
}
