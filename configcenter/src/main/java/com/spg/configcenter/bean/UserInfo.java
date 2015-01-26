/**
 * 
 */
package com.spg.configcenter.bean;

import io.netty.channel.Channel;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 项目名称：configcenter
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年7月15日 下午6:47:55
 * 
 * @version V1.0.0
 * 
 */
public class UserInfo implements Serializable
{
    /**
     * {变量说明}
     */
    private static final long serialVersionUID = 4752743734763086160L;

    private Long id;

    private String userName;

    private Timestamp lastLoginTime;

    private String serialNo;

    private Channel userChannel;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Timestamp getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public Channel getUserChannel()
    {
        return userChannel;
    }

    public void setUserChannel(Channel userChannel)
    {
        this.userChannel = userChannel;
    }

    @Override
    public String toString()
    {
        return "UserInfo [id=" + id + ", userName=" + userName + ", lastLoginTime=" + lastLoginTime
                + ", serialNo=" + serialNo + ", userChannel=" + userChannel + "]";
    }

}
