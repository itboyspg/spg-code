
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
 * @author Administrator
 *
 * @create_time：2014年9月3日 下午8:10:11
 *
 * @version V1.0.0
 *
 */
public class BaseResultInfo implements Serializable
{
    /**
    * {变量说明}
    */
    private static final long serialVersionUID = 1085428699636489670L;
    
    private int status;
    
    private String message;
    
    private Object data;

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "BaseResultInfo [status=" + status + ", message=" + message + ", data=" + data + "]";
    }
    
    
    
}

