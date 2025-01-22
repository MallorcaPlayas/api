package org.example.apirest.repository;

import org.example.apirest.model.RoadType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadTypeRepository extends JpaRepository<RoadType, Long> {
}
