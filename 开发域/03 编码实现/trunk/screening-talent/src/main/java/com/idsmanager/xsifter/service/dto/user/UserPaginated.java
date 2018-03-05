
package com.idsmanager.xsifter.service.dto.user;



import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.user.User;

import java.util.Map;

/**
 * 2015/12/19
 *
 * @author Shengzhao Li
 */
public class UserPaginated extends DefaultPaginated<User> {


    private String username;
    
    private String notUsername;

    public UserPaginated() {
    }

    @Override
    public Map<String, Object> queryMap() {
        final Map<String, Object> map = super.queryMap();
        map.put("username", username);
        map.put("notUsername", notUsername);
        return map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getNotUsername() {
		return notUsername;
	}

	public void setNotUsername(String notUsername) {
		this.notUsername = notUsername;
	}
    
}
