package org.example.apirest.dto.userRequireRole;

public class ApproveRequestDto {
    private Boolean approved;

    public ApproveRequestDto() {
    }
    public ApproveRequestDto(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
