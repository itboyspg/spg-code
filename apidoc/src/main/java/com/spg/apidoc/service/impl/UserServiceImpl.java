package com.spg.apidoc.service.impl;

import java.util.ArrayList;
import java.util.List;

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
    
    private static List<UserInfo> allUsers = new ArrayList<UserInfo>();
    
    static
    {
        UserInfo user = null;
        for (int i = 0; i < 2; i++)
        {
            user = new UserInfo((i+1), "name", i);
            allUsers.add(user);
        }
    }

    /**
     * @see com.spg.apidoc.service.UserService#addUser(com.spg.apidoc.po.UserInfo)
     */
    @Override
    public int addUser(UserInfo user)
    {
        LOGGER.debug(String.format("enter function"));
        allUsers.add(user);
        LOGGER.debug(String.format("exit function"));
        return 1;
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
        UserInfo user = allUsers.get(id);
        LOGGER.debug(String.format("exit function, %s", user));
        return user;
    }
}
