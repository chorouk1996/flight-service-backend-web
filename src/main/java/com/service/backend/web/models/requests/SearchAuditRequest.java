package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;
import jakarta.validation.constraints.Pattern;


public class SearchAuditRequest {

    private EntityTypeEnum entityType;
    private EntityActionEnum action;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be in format dd-MM-yyyy")
    private String startDate;

    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be in format dd-MM-yyyy")
    private String endDate;

    private int page= 0;

    private int size= 10;

    public EntityTypeEnum getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityTypeEnum entityType) {
        this.entityType = entityType;
    }

    public EntityActionEnum getAction() {
        return action;
    }

    public void setAction(EntityActionEnum action) {
        this.action = action;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
