/**
 * 
 */
package com.spg.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：forum
 * 
 * @description: 分页结果对象,默认当前页为第一页，每页10条记录，总页数会根据总记录数和每页数据大小计算
 * 
 * @author Wind-spg
 * 
 * @create_time：2014年10月15日 上午8:44:19
 * 
 * @version V1.0.0
 * @param <T>
 * 
 */
public class PageResultInfo<T> implements Serializable
{

    /**
     * {变量说明}
     */
    private static final long serialVersionUID = -5757236142215665303L;

    // private static final Log LOGGER =
    // LogFactory.getLog(PageResultInfo.class);

    // 默认每页数据大小
    private static final int DEFAULT_PAGE_SIZE = 10;

    // 默认当前页码
    private static final int DEFAULT_CURRENT_PAGE_NO = 1;

    // 当前页
    private int currentPageNo = DEFAULT_CURRENT_PAGE_NO;

    // 每页数据大小
    private int pageSize = DEFAULT_PAGE_SIZE;

    // 总页数
    private int pageCount;

    // 总记录数
    private int totalCount;

    private List<T> resultData;

    public PageResultInfo()
    {

    }

    public PageResultInfo(int currentPageNo, int pageSize, int pageCount, int totalCount, List<T> resultData)
    {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
        this.resultData = resultData;
    }
    
    public PageResultInfo(int currentPageNo, int pageSize, int totalCount, List<T> resultData)
    {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.resultData = resultData;
    }

    public int getCurrentPageNo()
    {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo)
    {
        if (currentPageNo > 0)
        {
            this.currentPageNo = currentPageNo;
        }
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        if (pageSize <= 0)
        {
            this.pageSize = 1;
        } else
        {
            this.pageSize = pageSize;
        }
    }

    // 计算总页数
    public int getPageCount()
    {
        if ((this.getTotalCount() % this.getPageSize()) > 0)
        {
            return (this.getTotalCount() / this.getPageSize()) + 1;
        } else
        {
            return this.getTotalCount() / this.getPageSize();
        }
    }

    /**
     * @description:
     * @author: Wind-spg
     * @param pageCount
     * @deprecated 总页数会自动计算，此处不需要设置
     */
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
        if (totalCount <= 0)
        {
            this.totalCount = 0;
        } else
        {
            this.totalCount = totalCount;
        }
    }

    public List<T> getResultData()
    {
        return resultData;
    }

    public void setResultData(List<T> resultData)
    {
        this.resultData = resultData;
    }

    @Override
    public String toString()
    {
        return "PageResultVo [currentPageNo=" + currentPageNo + ", pageSize=" + pageSize + ", pageCount="
                + pageCount + ", totalCount=" + totalCount + ", resultData=" + resultData + "]";
    }

}
