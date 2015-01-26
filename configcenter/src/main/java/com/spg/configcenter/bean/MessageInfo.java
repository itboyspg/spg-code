/**
 * 
 */
package com.spg.configcenter.bean;

import java.io.Serializable;

/**
 * 项目名称：configcenter
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年9月2日 上午10:12:36
 * 
 * @version V1.0.0
 * 
 */
public class MessageInfo implements Serializable
{
    /**
     * {变量说明}
     */
    private static final long serialVersionUID = 7120255023014056794L;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息是否加密
     */
    private byte isEncrypt;

    /**
     * 加密方式，如果不加密，则为空
     */
    private byte encryptType;

    /**
     * 消息长度
     */
    private byte messageLength;

    /**
     * 消息体
     */
    private Object message;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 用户信息，用于用户登陆时带用户基本信息上来
     */
    private UserInfo userInfo;

    private String fromId;

    private String toId;

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public byte getIsEncrypt()
    {
        return isEncrypt;
    }

    public void setIsEncrypt(byte isEncrypt)
    {
        this.isEncrypt = isEncrypt;
    }

    public byte getEncryptType()
    {
        return encryptType;
    }

    public void setEncryptType(byte encryptType)
    {
        this.encryptType = encryptType;
    }

    public byte getMessageLength()
    {
        return messageLength;
    }

    public void setMessageLength(byte messageLength)
    {
        this.messageLength = messageLength;
    }

    public Object getMessage()
    {
        return message;
    }

    public void setMessage(Object message)
    {
        this.message = message;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public String getFromId()
    {
        return fromId;
    }

    public void setFromId(String fromId)
    {
        this.fromId = fromId;
    }

    public String getToId()
    {
        return toId;
    }

    public void setToId(String toId)
    {
        this.toId = toId;
    }

    @Override
    public String toString()
    {
        return "MessageInfo [messageId=" + messageId + ", isEncrypt=" + isEncrypt + ", encryptType="
                + encryptType + ", messageLength=" + messageLength + ", message=" + message + ", title="
                + title + ", userInfo=" + userInfo + "]";
    }

}
