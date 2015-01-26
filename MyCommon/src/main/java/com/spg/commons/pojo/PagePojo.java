/**
 * 
 */
package com.spg.commons.pojo;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 项目名称：MyCommon
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年9月1日 上午10:21:47
 * 
 * @version V1.0.0
 * 
 */
public class PagePojo implements Serializable
{

    /**
     * {变量说明}
     */
    private static final long serialVersionUID = -7492087656174349852L;

    private static final Logger LOGGER = LogManager.getLogger(PagePojo.class);

    public PagePojo()
    {

    }

    public PagePojo(int pageNo, int pageSize)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PagePojo(int pageNo, int pageSize, int totalCount, Object pageData)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageData = pageData;
    }

    // 每页数据量
    private int pageSize;

    // 总页数
    private int pageCount;

    // 总记录数
    private int totalCount;

    // 当前页码
    private int pageNo;

    // 分页结果数据
    private Object pageData;

    public int getPageSize()
    {
        return (pageSize <= 0 ? 10 : pageSize);
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageCount()
    {
        if (this.getTotalCount() % this.getPageSize() > 0)
        {
            return this.getTotalCount() / this.getPageSize() + 1;
        } else
        {
            return this.getTotalCount() / this.getPageSize();
        }
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public int getPageNo()
    {
        return pageNo < 0 ? 1 : pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public Object getPageData()
    {
        return pageData;
    }

    public void setPageData(Object pageData)
    {
        this.pageData = pageData;
    }

    @Override
    public String toString()
    {
        return "PagePojo [pageSize=" + pageSize + ", pageCount=" + pageCount + ", totalCount=" + totalCount
                + ", pageNo=" + pageNo + ", pageData=" + pageData + "]";
    }

}
