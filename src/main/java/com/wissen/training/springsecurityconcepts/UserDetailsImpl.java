package com.wissen.training.springsecurityconcepts;

import com.wissen.training.springsecurityconcepts.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> roles;
    private String active;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.roles = Arrays.stream(user.getRoles().split(","))
                .map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        this.active = user.getActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return active.equals("1");
    }
}
