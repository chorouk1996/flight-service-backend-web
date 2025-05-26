package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;
import jakarta.validation.constraints.Pattern;

import lombok.Data;


@Data
public class SearchAuditRequest {

    private EntityTypeEnum entityType;
    private EntityActionEnum action;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be in format dd-MM-yyyy")
    private String startDate;

    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be in format dd-MM-yyyy")
    private String endDate;

    private int page= 0;

    private int size= 10;


}
