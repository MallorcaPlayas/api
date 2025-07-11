package org.example.apirest.controller.beach;


import org.example.apirest.controller.GeneralizedController;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.service.beachHasService.BeachHasServiceServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beach-has-service")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class BeachHasServiceController  extends GeneralizedController<BeachHasServiceDto, CreateBeachHasServiceDto> {
    public BeachHasServiceController(BeachHasServiceServiceImpl service) {
        super(service);
    }
}
