package org.example.apirest.dto.userRequireRole;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class CreateUserRequireRoleDto {
    private Long user_id;
    private Long role_id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean isApproved;
}
