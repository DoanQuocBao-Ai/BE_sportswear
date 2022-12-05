package com.store.sportswear.security;

import com.store.sportswear.entity.EUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public int id;
    private String userName;
    private String password;
    private String eMail;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(int id, String userName, String password, String eMail, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(EUser EUser) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(EUser.getId(), EUser.getUserName(), EUser.getPassword(), EUser.getEMail(), grantedAuthorityList);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
