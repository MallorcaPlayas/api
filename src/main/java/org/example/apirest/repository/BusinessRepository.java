package org.example.apirest.repository;

import org.example.apirest.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
