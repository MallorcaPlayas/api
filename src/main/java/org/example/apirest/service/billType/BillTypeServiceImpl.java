package org.example.apirest.service.billType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Bill;
import org.example.apirest.model.BillType;
import org.example.apirest.repository.BillRepository;
import org.example.apirest.repository.BillTypeRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillTypeServiceImpl extends GeneralizedServiceImpl<BillType, BillTypeDto, CreateBillTypeDto, BillTypeRepository> {
    public BillTypeServiceImpl(BillTypeRepository repository, DtoConverterImpl<BillType,BillTypeDto,CreateBillTypeDto> dtoConverter) {
        super(repository, dtoConverter, BillType.class, BillTypeDto.class);
    }
}
