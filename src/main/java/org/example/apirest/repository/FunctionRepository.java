package org.example.apirest.repository;

import org.example.apirest.model.Function;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionRepository extends JpaRepository<Function, Long> {
}
