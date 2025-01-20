package org.example.apirest.dto.userHasRole;

import lombok.Data;

import java.time.LocalDate;


@Data
public class CreateUserHasRoleDto {
    private Long user_id;
    private Long role_id;
    private LocalDate dateBegin;
    private LocalDate dateFinish;
}
