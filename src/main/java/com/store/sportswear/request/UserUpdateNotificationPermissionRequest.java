package com.store.sportswear.request;

import lombok.Data;

@Data
public class UserUpdateNotificationPermissionRequest {

    private int userId;

    private boolean permission;
}
