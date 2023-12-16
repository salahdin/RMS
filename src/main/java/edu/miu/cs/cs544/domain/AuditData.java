package edu.miu.cs.cs544.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
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

    public AuditData(String createdBy, String updatedBy, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
}
