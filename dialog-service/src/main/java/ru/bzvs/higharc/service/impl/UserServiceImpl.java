package ru.bzvs.higharc.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.bzvs.higharc.service.UserService;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Long extractCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication.getPrincipal()) ? ((UserDetailsImpl) authentication.getPrincipal()).getId() : null;
    }
}
