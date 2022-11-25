package com.store.sportswear.dto.viewDto;

import com.store.sportswear.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserViewDto implements Serializable {

    private static final Long serialVersionID = 1L;

    private final String userName;
    private final String eMail;
    private final String password;

    private UserViewDto(String userName, String eMail, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
    }

    public static UserViewDto of(User user) {
        return new UserViewDto(user.getUserName(), user.getPassword() ,user.getEMail());
    }

}
