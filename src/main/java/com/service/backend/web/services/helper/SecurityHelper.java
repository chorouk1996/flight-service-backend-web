package com.service.backend.web.services.helper;

import com.service.backend.web.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

    private SecurityHelper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }


    public static UserDetailsImpl getUserConnected (){
       return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
