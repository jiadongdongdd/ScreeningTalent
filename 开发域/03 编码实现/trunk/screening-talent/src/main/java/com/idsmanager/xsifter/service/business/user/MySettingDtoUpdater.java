
package com.idsmanager.xsifter.service.business.user;


import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.dto.user.MySettingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/12/21
 *
 * @author Shengzhao Li
 */
public class MySettingDtoUpdater {


    private static final Logger LOG = LoggerFactory.getLogger(MySettingDtoUpdater.class);

    private transient UserRepository userDao = BeanProvider.getBean(UserRepository.class);

    private MySettingDto formDto;

    public MySettingDtoUpdater(MySettingDto formDto) {
        this.formDto = formDto;
    }

    public void update() {

        final User user = userDao.currentUser();
        user.setPassword(formDto.getNewPassword());

        userDao.updateUserPassword(user);
        LOG.debug("{}|Update my-setting: change password", SecurityUtils.username());
    }
}
