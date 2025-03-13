package org.example.apirest.service.userRequireRole;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.document.DocumentDto;
import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Document;
import org.example.apirest.model.Role;
import org.example.apirest.model.User;
import org.example.apirest.model.UserRequireRole;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.repository.UserRequireRoleRepository;
import org.example.apirest.service.document.DocumentService;
import org.example.apirest.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequireRoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DtoConverter<Document,DocumentDto> documentDtoConverter;
    private final UserRequireRoleRepository repository;
    private final DtoConverterGeneralizedImpl<UserRequireRole, UserRequireRoleDto, CreateUserRequireRoleDto> dtoConverter;

    public List<UserRequireRoleDto> findAll() {
        List<UserRequireRole> requireRoles = repository.findAll();
        return requireRoles.stream()
                .map(requireRole -> {
                    UserRequireRoleDto requireRoleDto = dtoConverter.convertDto(requireRole,UserRequireRoleDto.class);
                    List<DocumentDto> documentDtos = documentDtoConverter.entityListToDtoList(requireRole.getDocuments());
                    requireRoleDto.setDocuments(documentDtos);
                    return requireRoleDto;
                }).toList();
    }

    public UserRequireRoleDto findOne(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        UserRequireRoleDto requireRoleDto = dtoConverter.convertDto(entity,UserRequireRoleDto.class);
        List<DocumentDto> documentDtos = documentDtoConverter.entityListToDtoList(entity.getDocuments());
        requireRoleDto.setDocuments(documentDtos);
        return requireRoleDto;
    }

    public void delete(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        repository.delete(entity);
    }

    public UserRequireRoleDto save(CreateUserRequireRoleDto entity) {
        UserRequireRole entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, UserRequireRole.class);
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        entityToInsert.setUser(user);
        entityToInsert.setRole(role);
        return dtoConverter.convertDto(repository.save(entityToInsert), UserRequireRoleDto.class);
    }

    public UserRequireRoleDto update(Long id, CreateUserRequireRoleDto entity) {
        UserRequireRole old = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        UserRequireRole newEntity = dtoConverter.convertToEntityFromCreateDto(entity, UserRequireRole.class);

        if (entity == null) {
            return null;
        }

        Utils.updateFields(old, newEntity);

        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        old.setUser(user);
        old.setRole(role);

        return dtoConverter.convertDto(repository.save(old), UserRequireRoleDto.class);
    }

    public UserRequireRoleDto approve(Long id , Boolean approve){
        UserRequireRole requireRole = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        requireRole.setIsApproved(approve);
        return dtoConverter.convertDto(repository.save(requireRole), UserRequireRoleDto.class);
    }
}
