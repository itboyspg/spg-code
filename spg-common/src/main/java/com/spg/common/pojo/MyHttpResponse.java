/**
 * 
 */
package com.spg.common.pojo;

import java.io.Serializable;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年12月12日 上午9:41:58
 * @version V1.0.0
 * 
 */
public class MyHttpResponse implements Serializable
{
    /**
     * {变量说明}
     */
    private static final long serialVersionUID = 6381662550803520971L;

    // private static final Log LOGGER = LogFactory.getLog(MyHttpResponse.class);

    private int responseStatus;

    private String responseBody;

    private String requetUrl;

    public MyHttpResponse()
    {

    }

    public MyHttpResponse(int resonseStatus, String responseBody)
    {
        this.responseStatus = resonseStatus;
        this.responseBody = responseBody;
    }

    public MyHttpResponse(int resonseStatus, String responseBody, String requestUrl)
    {
        this.responseStatus = resonseStatus;
        this.responseBody = responseBody;
        this.requetUrl = requestUrl;
    }

    public int getResponseStatus()
    {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus)
    {
        this.responseStatus = responseStatus;
    }

    public String getResponseBody()
    {
        return responseBody;
    }

    public void setResponseBody(String responseBody)
    {
        this.responseBody = responseBody;
    }

    public String getRequetUrl()
    {
        return requetUrl;
    }

    public void setRequetUrl(String requetUrl)
    {
        this.requetUrl = requetUrl;
    }

    @Override
    public String toString()
    {
        return "MyHttpResponse [responseStatus=" + responseStatus + ", responseBody=" + responseBody + ", requetUrl="
                + requetUrl + "]";
    }

}
