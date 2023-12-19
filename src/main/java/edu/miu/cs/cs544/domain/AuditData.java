package edu.miu.cs.cs544.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class AuditData {

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        //TODO: get the current user
        createdBy = "System";
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        //TODO: get the current user
        updatedBy = "System";
        updatedOn = LocalDateTime.now();
    }

    public AuditData(String createdBy, String updatedBy, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
}
