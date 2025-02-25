package org.example.apirest.repository;

import org.example.apirest.model.TypeBeach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeBeachRepository extends JpaRepository<TypeBeach, Long> {
}
