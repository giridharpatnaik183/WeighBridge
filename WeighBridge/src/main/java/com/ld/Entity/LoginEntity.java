package com.ld.Entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity

public class LoginEntity {
	@Id
   private String userId;
   private String password;
   
   @ManyToMany
   @JoinTable(name = "user_role_mapping",
              joinColumns = @JoinColumn(name = "userId"),
              inverseJoinColumns = @JoinColumn(name = "roleId"))
   private List<RoleMaster> roles;
   
   @Column(name = "roleId", insertable = false, updatable = false)
   private String roleId;

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public List<RoleMaster> getRoles() {
	return roles;
}

public void setRoles(List<RoleMaster> roles) {
	this.roles = roles;
}

public String getRoleId() {
	return roleId;
}

public void setRoleId(String roleId) {
	this.roleId = roleId;
}
   
}