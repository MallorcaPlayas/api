package org.example.apirest.repository;

import org.example.apirest.model.BudgetBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetBusinessRepository extends JpaRepository<BudgetBusiness, Long> {
}
