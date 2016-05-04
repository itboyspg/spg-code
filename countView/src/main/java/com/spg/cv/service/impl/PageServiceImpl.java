package com.spg.cv.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.spg.cv.service.PageService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月7日 下午10:20:51
 * @version V1.0.0
 */
@Service("pageManager")
public class PageServiceImpl extends CommonServiceImpl implements PageService
{
    private static final Log LOGGER = LogFactory.getLog(PageServiceImpl.class);

    @Override
    public Long addPageView(String key, String pageName)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, pageName));
        Long result = addData(key, pageName, 1L);
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

}
