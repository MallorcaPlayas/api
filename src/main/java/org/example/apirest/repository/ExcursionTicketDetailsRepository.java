package org.example.apirest.repository;

import org.example.apirest.model.ExcursionTicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionTicketDetailsRepository extends JpaRepository<ExcursionTicketDetails, Long> {
}
