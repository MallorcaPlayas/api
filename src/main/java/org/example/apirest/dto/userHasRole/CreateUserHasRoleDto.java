package org.example.apirest.dto.userHasRole;

import lombok.Data;

import java.time.LocalDate;


@Data
public class CreateUserHasRoleDto {
    private Long userId;
    private Long roleId;
    private LocalDate dateBegin;
    private LocalDate dateFinish;
}
