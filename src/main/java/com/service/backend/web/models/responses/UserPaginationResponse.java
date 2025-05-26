package com.service.backend.web.models.responses;


import lombok.Data;

import java.util.List;

@Data
public class UserPaginationResponse {

   List<CreateUserResponse> users;

   int page ;

   int size;

    long totalElements;


}
