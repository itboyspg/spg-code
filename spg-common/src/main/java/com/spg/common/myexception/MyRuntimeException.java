/**
 * 
 */
package com.spg.common.myexception;

/**
 * 项目名称：spg-common
 * 
 * @description: 自定义运行期异常
 * @author Wind-spg
 * @create_time：2014年11月21日 下午5:27:13
 * @version V1.0.0
 */
public class MyRuntimeException extends RuntimeException
{

    /**
     * {变量说明}
     */
    private static final long serialVersionUID = -1361667469272220502L;

    private final String EXCEPTION_CODE;

    public MyRuntimeException(String message)
    {
        super(message);
        this.EXCEPTION_CODE = "";
    }

    public MyRuntimeException(String exceptionCode, String message)
    {
        super(message);
        this.EXCEPTION_CODE = exceptionCode;
    }

    public MyRuntimeException(String exceptionCode, String message, Throwable cause)
    {
        super(message, cause);
        this.EXCEPTION_CODE = exceptionCode;
    }

    public MyRuntimeException(String exceptionCode, Throwable cause)
    {
        super(cause);
        this.EXCEPTION_CODE = exceptionCode;
    }

    public String getExceptionCode()
    {
        return this.EXCEPTION_CODE;
    }
}
