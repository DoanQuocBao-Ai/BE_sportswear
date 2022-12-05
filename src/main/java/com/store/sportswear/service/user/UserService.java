package com.store.sportswear.service.user;

import com.store.sportswear.dto.viewDto.UserViewDto;
import com.store.sportswear.entity.EUser;
import com.store.sportswear.request.UserDeleteRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    EUser add(EUser createDto);
    List<EUser> getAll();
    EUser getById(int id);
    List<EUser> slice(Pageable pageable);
    void deleteById(int id);
    List<UserViewDto> getUserViewDto();
    EUser getByUserName(String userName);
    void authDeleteByUser(UserDeleteRequest userDeleteRequest);
    EUser findByEMail(String eMail);
    void updateByUserName(int userId, String userName);
    void updateByNotificationPermission(int userId, boolean permission);
}
