package logdef.humanResourcesManagementSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import logdef.humanResourcesManagementSystem.entities.concretes.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	
	Role getByRoleName(String roleName);
	
	Role getByRoleId(int roleId);

}
