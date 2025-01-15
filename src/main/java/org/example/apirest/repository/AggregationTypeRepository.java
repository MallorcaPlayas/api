package org.example.apirest.repository;

import org.example.apirest.model.AggregationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregationTypeRepository extends JpaRepository<AggregationType, Long> {
}
