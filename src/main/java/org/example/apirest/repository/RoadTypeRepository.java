package org.example.apirest.repository;

import org.example.apirest.model.RoadType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadTypeRepository extends JpaRepository<RoadType, Long> {
}
