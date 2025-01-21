package org.example.apirest.service.bill;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BeachManager;
import org.example.apirest.model.Bill;
import org.example.apirest.repository.BeachManagerRepository;
import org.example.apirest.repository.BeachRepository;
import org.example.apirest.repository.BillRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl extends GeneralizedServiceImpl<Bill, BillDto, CreateBillDto, BillRepository> {
    public BillServiceImpl(BillRepository repository, DtoConverterImpl<Bill,BillDto,CreateBillDto> dtoConverter) {
        super(repository, dtoConverter, Bill.class, BillDto.class);
    }
}
