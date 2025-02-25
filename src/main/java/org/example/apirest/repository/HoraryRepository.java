package org.example.apirest.repository;

import org.example.apirest.model.Horary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraryRepository extends JpaRepository<Horary, Long> {
}
