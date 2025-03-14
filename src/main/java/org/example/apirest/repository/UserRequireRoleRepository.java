package org.example.apirest.repository;

import org.example.apirest.model.UserRequireRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRequireRoleRepository extends JpaRepository<UserRequireRole, Long> {
     List<UserRequireRole> findAll();

     @Query("SELECT DISTINCT ur FROM UserRequireRole ur JOIN FETCH ur.user JOIN FETCH ur.role")
     List<UserRequireRole> findAllDistinct();
}
