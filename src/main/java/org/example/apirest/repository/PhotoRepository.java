package org.example.apirest.repository;

import org.example.apirest.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<List<Photo>> findByIsPrivateFalse();
    Optional<List<Photo>> findByUserUserName(String username);
}
