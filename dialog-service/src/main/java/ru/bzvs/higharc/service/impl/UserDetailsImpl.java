package ru.bzvs.higharc.service.impl;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.bzvs.higharc.dto.UserDto;

import java.util.Collection;

@Getter
public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String username;

    private String password;

    public UserDetailsImpl(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static UserDetails ofUser(UserDto user) {
        return new UserDetailsImpl(user.getId(), user.getFirstName(), user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
