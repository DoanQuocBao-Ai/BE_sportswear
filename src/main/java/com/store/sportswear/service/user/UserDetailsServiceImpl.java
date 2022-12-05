package com.store.sportswear.service.user;

import com.store.sportswear.dto.RoleDao;
import com.store.sportswear.dto.UserDao;
import com.store.sportswear.entity.EUser;
import com.store.sportswear.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        EUser eUser = this.userDao.findUserAccount(username);

        if (eUser == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        System.out.println("Found User: " + eUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.roleDao.getRoleNames(eUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(eUser.getUserName(), //
                eUser.getPassword(), grantList);

        return userDetails;
        //EUser EUser = userService.getByUserName(username);
        //return JwtUserDetails.create(EUser);
    }

    public UserDetails loadUserById(int id) {
        EUser EUser = userService.getById(id);
        return JwtUserDetails.create(EUser);
    }

}
