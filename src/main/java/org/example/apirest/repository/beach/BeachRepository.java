package org.example.apirest.repository.beach;

import org.example.apirest.model.beach.Beach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeachRepository extends JpaRepository<Beach,Long> {
}
