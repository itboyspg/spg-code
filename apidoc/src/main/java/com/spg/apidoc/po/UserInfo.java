package com.spg.apidoc.po;

import java.io.Serializable;

/**
 * 项目名称：apidoc
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年2月3日 上午10:26:02
 * @version V1.0.0
 *
 */
public class UserInfo implements Serializable
{
    /**
     * {变量说明}
     */
    private static final long serialVersionUID = 5402516802630861439L;

    // private static final Log LOGGER = LogFactory.getLog(UserInfo.class);

    private int id;

    private String name;

    private int age;

    public UserInfo()
    {

    }

    public UserInfo(int id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "UserInfo [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}
