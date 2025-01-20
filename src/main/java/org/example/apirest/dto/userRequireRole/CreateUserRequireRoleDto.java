package org.example.apirest.dto.userRequireRole;

import lombok.Data;

import java.time.LocalDate;


@Data
public class CreateUserRequireRoleDto {
    private Long user_id;
    private Long role_id;
    private String urlPhotoDni;
    private String urlOfficialDoc;
    private boolean isApproved;
}
