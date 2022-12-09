package com.example.standard.Repositories;

import com.example.standard.Core.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepositoty extends JpaRepository<Permission,Integer> {
}
