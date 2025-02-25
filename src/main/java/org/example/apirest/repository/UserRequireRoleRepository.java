package org.example.apirest.repository;

import org.example.apirest.model.UserRequireRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequireRoleRepository extends JpaRepository<UserRequireRole, Long> {
}
