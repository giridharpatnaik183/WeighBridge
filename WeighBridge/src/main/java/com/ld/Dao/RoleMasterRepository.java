package com.ld.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ld.Entity.RoleMaster;

public interface RoleMasterRepository extends JpaRepository<RoleMaster, String> {

	public RoleMaster findByRoleName(String role);
}
