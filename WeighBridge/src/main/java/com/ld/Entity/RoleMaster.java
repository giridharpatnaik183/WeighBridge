package com.ld.Entity;

import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity

public class RoleMaster {
	@Id
   private String roleId;
    private String roleName;
    
    
    @ManyToMany(mappedBy = "roles")
    private List<LoginEntity> user;


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<LoginEntity> getUser() {
		return user;
	}


	public void setUser(List<LoginEntity> user) {
		this.user = user;
	}
    
}
