package com.idsmanager.xsifter.domain.user;


import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.utils.UUIDGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统账号
 *
 * @author Shengzhao Li
 */

@Document(collection = "user_")
public class User implements Serializable {


    private static final long serialVersionUID = -1958822224307828242L;

    @Id
    private String guid = UUIDGenerator.generate();
    @CreatedDate
    private Date createTime = DateUtils.now();

    private String username;

    private String password;
    
    private boolean disabled = false;//false:正常 true:已注销


    private Set<Privilege> privileges = new HashSet<>();


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public void setPassword(String originalPass) {
        this.password = PasswordHandler.encryptPassword(originalPass, this.username);
    }


    public void setPrivileges(List<Privilege> privileges) {
        this.privileges.addAll(privileges);
    }

    public String password() {
        return this.password;
    }

    public String username() {
        return this.username;
    }

    public String guid() {
        return this.guid;
    }

    public Date createTime() {
        return createTime;
    }

    public String getCreateTime() {
        return DateUtils.toDateTime(createTime);
    }

    public String getGuid() {
        return guid;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", guid='" + guid + '\'' +
                ", privileges=" + privileges +
                '}';
    }

    public boolean isAdmin() {
        return this.privileges.contains(Privilege.ADMIN);
    }
    public boolean isEnterpriseAdmin() {
        return this.privileges.contains(Privilege.ENTERPRISEADMIN);
    }

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
    
    
}
