package org.example.apirest.repository;

import org.example.apirest.model.BusinessHorary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessHoraryRepository extends JpaRepository<BusinessHorary, Long> {
}
