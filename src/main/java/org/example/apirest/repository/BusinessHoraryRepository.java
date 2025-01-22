package org.example.apirest.repository;

import org.example.apirest.model.BusinessHorary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoraryRepository extends JpaRepository<BusinessHorary, Long> {
}
