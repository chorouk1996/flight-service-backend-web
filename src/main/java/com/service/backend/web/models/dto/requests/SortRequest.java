package com.service.backend.web.models.dto.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.backend.web.models.enumerators.SortDirectionEnum;

public class SortRequest {


    @JsonProperty("field")
    private String sortField;


    @JsonProperty("direction")
    private SortDirectionEnum sortDirection;


    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public SortDirectionEnum getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirectionEnum sortDirection) {
        this.sortDirection = sortDirection;
    }
}
