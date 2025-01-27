package org.example.apirest.repository;

import org.example.apirest.model.Beach;
import org.example.apirest.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    List<Camera> findAllByBeach(Beach beach);
}
