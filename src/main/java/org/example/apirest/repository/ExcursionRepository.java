package org.example.apirest.repository;

import org.example.apirest.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
