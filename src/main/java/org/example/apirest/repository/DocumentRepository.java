package org.example.apirest.repository;

import org.example.apirest.model.Document;
import org.example.apirest.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
