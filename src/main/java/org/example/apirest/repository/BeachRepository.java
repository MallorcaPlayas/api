package org.example.apirest.repository;

import org.example.apirest.model.Beach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeachRepository extends JpaRepository<Beach,Long> {
}
