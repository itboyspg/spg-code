package com.spg.apidoc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.spg.apidoc.po.UserInfo;
import com.spg.apidoc.service.UserService;

/**
 * 项目名称：apidoc
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年2月3日 上午10:28:43
 * @version V1.0.0
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
    private static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);

    /**
     * @see com.spg.apidoc.service.UserService#addUser(com.spg.apidoc.po.UserInfo)
     */
    @Override
    public int addUser(UserInfo user)
    {
        LOGGER.debug(String.format("enter function"));
        LOGGER.debug(String.format("exit function"));
        return 0;
    }

    /**
     * @see com.spg.apidoc.service.UserService#deleteUser(int)
     */
    @Override
    public int deleteUser(int id)
    {
        LOGGER.debug(String.format("enter function"));
        LOGGER.debug(String.format("exit function"));
        return 0;
    }

    /**
     * @see com.spg.apidoc.service.UserService#updateUser(int,
     *      com.spg.apidoc.po.UserInfo)
     */
    @Override
    public int updateUser(int id, UserInfo user)
    {
        LOGGER.debug(String.format("enter function"));
        LOGGER.debug(String.format("exit function"));
        return 0;
    }

    @Override
    public UserInfo queryUserById(int id)
    {
        LOGGER.debug(String.format("enter function"));
        LOGGER.debug(String.format("exit function"));
        return null;
    }
}
