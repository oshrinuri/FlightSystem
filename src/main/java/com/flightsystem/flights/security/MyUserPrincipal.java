package com.flightsystem.flights.security;

import com.flightsystem.flights.dtos.User;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
public class MyUserPrincipal implements UserDetails {
    private final transient User user;
    private final long internalUserId;
    /* ------------------------------------------------------------------------------------------------------------------- */
    public MyUserPrincipal(User user,long internalUserId) {
        this.user = user;
        this.internalUserId = internalUserId;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listAuthorities = new ArrayList<>();
        listAuthorities.add(switch (user.getUserRole()) {
            case 1 -> new SimpleGrantedAuthority("ROLE_CUSTOMER");
            case 2 -> new SimpleGrantedAuthority("ROLE_ADMIN");
            case 3 -> new SimpleGrantedAuthority("ROLE_AIRLINE");
            default -> throw new IllegalStateException("Unexpected value: " + user.getUserRole());
        });
        return listAuthorities;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    public long getInternalId() {
        return this.internalUserId;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
