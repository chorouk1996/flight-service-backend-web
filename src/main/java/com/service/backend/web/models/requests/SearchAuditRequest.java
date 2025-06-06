package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "SearchAuditRequest", description = "Search filter to query audit logs by entity, action, and date range.")
public class SearchAuditRequest {

    @Schema(description = "Entity type to filter audit logs by (e.g., USER, BOOKING, FLIGHT)", example = "USER")
    private EntityTypeEnum entityType;

    @Schema(description = "Entity action to filter audit logs by (e.g., CREATE, UPDATE, DELETE)", example = "CREATE")
    private EntityActionEnum action;

    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be in format dd-MM-yyyy")
    @Schema(description = "Start date for audit filtering in dd-MM-yyyy format", example = "01-05-2025")
    private String startDate;

    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be in format dd-MM-yyyy")
    @Schema(description = "End date for audit filtering in dd-MM-yyyy format", example = "31-05-2025")
    private String endDate;

    @Schema(description = "Page number for pagination", example = "0", defaultValue = "0")
    private int page = 0;

    @Schema(description = "Number of records per page (max 100)", example = "10", defaultValue = "10")
    private int size = 10;
}