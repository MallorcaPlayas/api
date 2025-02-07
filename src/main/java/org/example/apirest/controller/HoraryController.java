package org.example.apirest.controller;

import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.service.horary.HoraryServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horaries")
@CrossOrigin(origins = "*")
public class HoraryController extends GeneralizedController<HoraryDto, CreateHoraryDto> {
    public HoraryController(HoraryServiceImpl service) {
        super(service);
    }
}
