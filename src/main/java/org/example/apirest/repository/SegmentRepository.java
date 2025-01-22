package org.example.apirest.repository;

import org.example.apirest.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentRepository extends JpaRepository<Segment, Long> {
}
