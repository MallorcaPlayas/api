package org.example.apirest.repository;

import org.example.apirest.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
