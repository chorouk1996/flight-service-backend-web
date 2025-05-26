package com.service.backend.web.models.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.backend.web.models.enumerators.SortDirectionEnum;

import lombok.Data;

@Data
public class SortRequest {


    @JsonProperty("field")
    private String sortField;


    @JsonProperty("direction")
    private SortDirectionEnum sortDirection;



}
