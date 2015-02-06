package com.spg.apidoc.service;

import com.spg.apidoc.po.UserInfo;

/**
 * 项目名称：apidoc
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年2月3日 上午10:23:58
 * @version V1.0.0
 *
 */
public interface UserService
{

    int addUser(UserInfo user);

    int deleteUser(int id);

    int updateUser(int id, UserInfo user);
    
    UserInfo queryUserById(int id);
}
