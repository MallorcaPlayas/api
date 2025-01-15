package org.example.apirest.service.bill;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Bill;
import org.example.apirest.repository.BillRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository repository;
    private final DtoConverterImpl<Bill, BillDto, CreateBillDto> dtoConverter;

    @Override
    public List<BillDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), BillDto.class);
    }

    @Override
    public BillDto findOne(Long id) {
        Bill bill = repository.findById(id).orElseThrow(() -> new NotFoundException(Bill.class, id));
        return dtoConverter.convertDto(bill, BillDto.class);
    }

    @Override
    public BillDto save(CreateBillDto bill) {
        Bill billToInsert = dtoConverter.convertToEntityFromCreateDto(bill, Bill.class);
        return dtoConverter.convertDto(repository.save(billToInsert), BillDto.class);
    }

    @Override
    public BillDto update(Long id, CreateBillDto bill) {
        Bill oldBill = repository.findById(id).orElseThrow(() -> new NotFoundException(Bill.class, id));
        Bill billToInsert = dtoConverter.convertToEntityFromCreateDto(bill, Bill.class);

        if (oldBill == null) {
            return null;
        }

        UtilsClass.updateFields(oldBill, billToInsert);

        return dtoConverter.convertDto(repository.save(oldBill), BillDto.class);
    }

    @Override
    public void delete(Long id) {
        Bill bill = repository.findById(id).orElseThrow(() -> new NotFoundException(Bill.class, id));
        repository.delete(bill);
    }
}
