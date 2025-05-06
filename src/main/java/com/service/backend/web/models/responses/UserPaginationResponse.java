package com.service.backend.web.models.responses;

import java.util.List;

public class UserPaginationResponse {

   List<CreateUserResponse> users;

   int page ;

   int size;

    long totalElements;

    public List<CreateUserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<CreateUserResponse> users) {
        this.users = users;
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

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
