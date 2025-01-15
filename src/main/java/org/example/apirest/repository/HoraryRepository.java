package org.example.apirest.repository;

import org.example.apirest.model.Horary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoraryRepository extends JpaRepository<Horary, Long> {
}
