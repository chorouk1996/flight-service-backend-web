package com.service.backend.web.models.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserPaginationResponse {

   List<CreateUserResponse> users;

   int page ;

   int size;

    long totalElements;


}
