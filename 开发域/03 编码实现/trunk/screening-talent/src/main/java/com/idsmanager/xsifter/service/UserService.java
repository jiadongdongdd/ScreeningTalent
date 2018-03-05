package com.idsmanager.xsifter.service;


import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.dto.password.ModifyAdminPasswordFormDto;
import com.idsmanager.xsifter.service.dto.user.MySettingDto;
import com.idsmanager.xsifter.service.dto.user.UserFormDto;
import com.idsmanager.xsifter.service.dto.user.UserPaginated;

public interface UserService {
    boolean isExistedUsername(String username);

    User getUserByUsername(String username);

    UserPaginated loadUserPaginated(UserPaginated paginated);

    String createUser(UserFormDto formDto);

    void archiveUserByGuid(String guid);

    void updateMySetting(MySettingDto formDto);

    User getUserByGuid(String guid);

    String initialDefaultUser();

	void modifyPassword(ModifyAdminPasswordFormDto formDto);

	Boolean updateUserState(String username);

	Boolean updateUserStateOpen(String username);
}
