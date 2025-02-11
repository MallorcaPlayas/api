package org.example.apirest.service.bill;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Bill;
import org.example.apirest.repository.BillRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements DtoConverter<Bill,BillDto, CreateBillDto> {

    private final BillRepository repository;
    private final ModelMapper mapper;

    public List<BillDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public BillDto findOne(Long id) {
        Bill bill = repository.findById(id).orElseThrow(()-> new NotFoundException(Bill.class,id));
        return this.toDto(bill);
    }

    public BillDto save(CreateBillDto entity) {
        Bill bill = fromDto(entity);
        return toDto(repository.save(bill));
    }

    public BillDto update(Long id, CreateBillDto createEntity) {
        Bill oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(Bill.class, id));
        Bill entityToInsert = this.fromDto(createEntity);

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return this.toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        Bill entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Bill.class,id));
        repository.delete(entity);
    }

    @Override
    public BillDto toDto(Bill bill) {
        return mapper.map(bill,BillDto.class);
    }

    @Override
    public List<BillDto> toDtoList(List<Bill> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Bill fromDto(CreateBillDto createBillDto) {
        return mapper.map(createBillDto,Bill.class);
    }

    @Override
    public List<Bill> fromDtoList(List<CreateBillDto> createBillDtos) {
        return createBillDtos.stream().map(this::fromDto).toList();
    }
}
