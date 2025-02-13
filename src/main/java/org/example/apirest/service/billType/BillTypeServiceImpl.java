package org.example.apirest.service.billType;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.example.apirest.model.BillType;
import org.example.apirest.repository.BillTypeRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BillTypeServiceImpl extends GeneralizedServiceImpl<BillType, BillTypeDto, CreateBillTypeDto, BillTypeRepository> {
    public BillTypeServiceImpl(BillTypeRepository repository, DtoConverterGeneralizedImpl<BillType,BillTypeDto,CreateBillTypeDto> dtoConverter) {
        super(repository, dtoConverter, BillType.class, BillTypeDto.class);
    }
}
