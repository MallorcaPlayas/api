package org.example.apirest.repository.beach;

import org.example.apirest.model.BeachManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeachManagerRepository extends JpaRepository<BeachManager, Long> {
}
