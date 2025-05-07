package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

public class SearchAuditRequest {

    private EntityTypeEnum entityType;
    private EntityActionEnum action;
    private LocalDate fromDate;
    private LocalDate toDate;

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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
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
