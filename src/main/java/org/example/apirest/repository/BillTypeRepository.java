package org.example.apirest.repository;

import org.example.apirest.model.BillType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillTypeRepository extends JpaRepository<BillType, Long> {
}
