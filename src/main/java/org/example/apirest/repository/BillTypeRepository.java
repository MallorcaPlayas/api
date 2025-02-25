package org.example.apirest.repository;

import org.example.apirest.model.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTypeRepository extends JpaRepository<BillType, Long> {
}
