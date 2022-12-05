package com.store.sportswear.service.user;

import com.store.sportswear.dto.viewDto.UserViewDto;
import com.store.sportswear.entity.EUser;
import com.store.sportswear.exception.NotFoundException;
import com.store.sportswear.repository.UserRepository;
import com.store.sportswear.request.UserDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public EUser add(EUser EUserCreateDto) {
         this.userRepository.save(new EUser(EUserCreateDto.getUserName(),
                EUserCreateDto.getPassword(), EUserCreateDto.getEMail(), EUserCreateDto.getUserCreateDate(), EUserCreateDto.isNotificationPermission()));
        return EUserCreateDto;
    }

    @Override
    public List<EUser> getAll() {
        final List<EUser> EUsers = this.userRepository.findAll();
        return EUsers;
    }

    @Override
    public EUser getById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user couldn't be found by following id: " + id));
    }

    @Override
    public List<EUser> slice(Pageable pageable) {
        final List<EUser> EUsers = this.userRepository.findAll(pageable).stream().collect(Collectors.toList());
        return EUsers;
    }

    @Override
    public void deleteById(int id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<UserViewDto> getUserViewDto() {
        final List<UserViewDto> users = this.userRepository.findAll().stream().map(UserViewDto::of).collect(Collectors.toList());
        return users;
    }

   @Override
    public EUser getByUserName(String userName) {
        return userRepository.findByUserName(userName);
   }

    @Override
    public void authDeleteByUser(UserDeleteRequest userDeleteRequest) {
        EUser EUser = userRepository.findByEMail(userDeleteRequest.getEMail());
        userRepository.deleteById(EUser.getId());
    }

    @Override
    public EUser findByEMail(String eMail) {
        return userRepository.findByEMail(eMail);
    }

    @Override
    public void updateByUserName(int userId, String userName) {
        Optional<EUser> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().setUserName(userName);
            userRepository.save(user.get());
        }
    }

    @Override
    public void updateByNotificationPermission(int userId, boolean permission) {
        Optional<EUser> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().setNotificationPermission(permission);
            userRepository.save(user.get());
        }

    }
}
