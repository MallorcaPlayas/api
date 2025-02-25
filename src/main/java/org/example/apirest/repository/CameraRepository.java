package org.example.apirest.repository;

import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
    List<Camera> findAllByBeach(Beach beach);
}
