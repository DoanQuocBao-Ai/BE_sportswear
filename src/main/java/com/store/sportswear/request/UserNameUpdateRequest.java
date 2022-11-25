package com.store.sportswear.request;

import lombok.Data;

@Data
public class UserNameUpdateRequest {

    private int userId;

    private String userName;
}
