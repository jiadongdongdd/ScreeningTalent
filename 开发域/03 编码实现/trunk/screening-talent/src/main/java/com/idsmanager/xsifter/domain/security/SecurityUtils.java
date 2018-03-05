package com.idsmanager.xsifter.domain.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.idsmanager.xsifter.domain.user.Privilege;

public class SecurityUtils {


    private static SecurityHolder securityHolder;

    public void setSecurityHolder(SecurityHolder securityHolder) {
        SecurityUtils.securityHolder = securityHolder;
    }

    public static UserDetails userDetails() {
        return securityHolder.userDetails();
    }

    public static String username() {
        return securityHolder != null ? securityHolder.username() : null;
    }
    public static java.util.List<Privilege> privileges() {
        return securityHolder != null ? securityHolder.privileges() : null;
    }
    public static String currentUserGuid() {
        final UserDetails userDetails = userDetails();
        if (userDetails instanceof IdsUserDetails) {
            return ((IdsUserDetails) userDetails).currentGuid();
        }
        return null;
    }


}	
