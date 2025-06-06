package com.service.backend.web.models.responses;


import lombok.Data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "UserPaginationResponse", description = "Paginated response containing a list of users and pagination details.")
public class UserPaginationResponse {

    @Schema(description = "List of users in the current page")
    private List<CreateUserResponse> users;

    @Schema(description = "Current page number", example = "0")
    private int page;

    @Schema(description = "Number of items per page", example = "10")
    private int size;

    @Schema(description = "Total number of users across all pages", example = "245")
    private long totalElements;
}