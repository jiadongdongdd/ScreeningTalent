package com.idsmanager.xsifter.domain.security;


import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


public class IdsUserDetails implements UserDetails {


    /**
     *
     */
    private static final long serialVersionUID = -2513888014140057691L;

    protected static final String ROLE_PREFIX = "ROLE_";

    protected User user;

    protected Company company;

    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public IdsUserDetails() {

    }

    public IdsUserDetails(User user) {
        this.user = user;
        initialAuthorities(user.getPrivileges());
    }

    public IdsUserDetails(Company company) {
        this.company = company;
        //Fixed
        initialAuthorities(Collections.singleton(Privilege.ENTERPRISE));
    }

    private void initialAuthorities(Set<Privilege> privileges) {

        for (Privilege privilege : privileges) {
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege));
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        if (company != null) {
            return company.getPassword();
        }
        return user.password();
    }

    @Override
    public String getUsername() {
        if (company != null) {
            return company.getUsername();
        }
        return user.username();
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


    public User user() {
        return this.user;
    }

    public Company company() {
        return this.company;
    }

    public String currentGuid() {
        if (company != null) {
            return company.getGuid();
        }
        return this.user.getGuid();
    }
}
