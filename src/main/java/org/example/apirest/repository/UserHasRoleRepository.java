package org.example.apirest.repository;

import org.example.apirest.model.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Long> {
}
