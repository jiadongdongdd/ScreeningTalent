
package com.idsmanager.xsifter.service.dto.user;


import com.idsmanager.xsifter.domain.user.Privilege;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2015/12/19
 *
 * @author Shengzhao Li
 */
public class UserFormDto implements Serializable {
    private static final long serialVersionUID = -8251954993624473451L;


    private String username;

    private String password;

    private String rePassword;

    protected Set<Privilege> privileges = new HashSet<>();


    public UserFormDto() {
    }


    public List<Privilege> getAllPrivileges() {
        return Arrays.asList(Privilege.ADMIN,Privilege.ENTERPRISEADMIN,Privilege.ENTERPRISE);
    }

    public List<Privilege> getAdminPrivileges() {
        return Arrays.asList(Privilege.ENTERPRISEADMIN);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
