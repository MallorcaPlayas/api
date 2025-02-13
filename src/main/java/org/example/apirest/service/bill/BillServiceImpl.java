package org.example.apirest.service.bill;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.model.Bill;
import org.example.apirest.repository.BillRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends GeneralizedServiceImpl<Bill, BillDto, CreateBillDto, BillRepository> {
    public BillServiceImpl(BillRepository repository, DtoConverterGeneralizedImpl<Bill,BillDto,CreateBillDto> dtoConverter) {
        super(repository, dtoConverter, Bill.class, BillDto.class);
    }
}
