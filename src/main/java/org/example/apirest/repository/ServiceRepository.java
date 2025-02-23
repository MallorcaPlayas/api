package org.example.apirest.repository;

import org.example.apirest.model.ServiceBeach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceBeach, Long> {
}
