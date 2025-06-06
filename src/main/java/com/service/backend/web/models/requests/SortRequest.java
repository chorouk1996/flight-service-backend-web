package com.service.backend.web.models.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.backend.web.models.enumerators.SortDirectionEnum;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "SortRequest", description = "Represents sorting options for a search request.")
public class SortRequest {

    @JsonProperty("field")
    @Schema(description = "Field name to sort by (e.g., price, departureTime)", example = "price")
    private String sortField;

    @JsonProperty("direction")
    @Schema(description = "Sort direction (ASC for ascending, DESC for descending)", example = "ASC")
    private SortDirectionEnum sortDirection;
}