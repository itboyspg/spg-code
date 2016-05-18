package com.spg.cv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.dao.RedisListAPIUtil;
import com.spg.cv.dao.RedisMapAPIUtil;
import com.spg.cv.po.PVBean;
import com.spg.cv.service.PageService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月7日 下午10:20:51
 * @version V1.0.0
 */
@Service("pageService")
public class PageServiceImpl extends CommonServiceImpl implements PageService
{
    private static final Log LOGGER = LogFactory.getLog(PageServiceImpl.class);

    @Override
    public Long addPageView(String key, String pageName)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, pageName));
        Long result = 0L;
        if (StringUtils.isNotEmpty(pageName) && judgePageName(DataType.PAGE_VIEW, pageName))
        {
            result = addData(key, pageName, 1L);
        }
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public String queryPVDataByKeyField(String key, String field)
    {
        LOGGER.debug(String.format("enter function"));
        String result = queryDataByKeyField(key, field);
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public Map<String, String> getAllPVData(String key)
    {
        LOGGER.debug(String.format("enter function"));
        Map<String, String> result = RedisMapAPIUtil.hgetAll(key);
        LOGGER.debug(String.format("exit function, %s", result.size()));
        return result;
    }

    /**
     * @description: 对pageName进行校验，判断此pageName是否为系统所配置的。<br>
     *               如果页面数过多，可能会有部分性能影响。
     * @author: Wind-spg
     * @param dataType
     * @param pageName
     * @return
     */
    private boolean judgePageName(DataType dataType, String pageName)
    {
        List<String> pageViewConfig = RedisListAPIUtil.queryListData(dataType.getName());
        List<PVBean> pvBeanList = new ArrayList<PVBean>();
        for (String str : pageViewConfig)
        {
            pvBeanList.add(JSON.parseObject(str, PVBean.class));
        }
        for (PVBean pvBean : pvBeanList)
        {
            if (pvBean.getEnglishName().equals(pageName))
            {
                return true;
            }
        }
        return false;
    }
}
