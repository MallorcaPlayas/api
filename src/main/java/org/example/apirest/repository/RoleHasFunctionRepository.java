package org.example.apirest.repository;

import org.example.apirest.model.RoleHasFunction;
import org.example.apirest.model.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleHasFunctionRepository extends JpaRepository<RoleHasFunction, Long> {
}
