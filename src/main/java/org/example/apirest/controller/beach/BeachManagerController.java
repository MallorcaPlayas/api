package org.example.apirest.controller.beach;

import org.example.apirest.controller.GeneralizedController;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.service.beachManager.BeachManagerServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beach-manager")
@CrossOrigin(origins = "*")
public class BeachManagerController extends GeneralizedController<BeachManagerDto, CreateBeachManagerDto> {
    public BeachManagerController(BeachManagerServiceImpl service) {
        super(service);
    }
}
