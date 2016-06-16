package com.spg.cv.po;

import java.io.Serializable;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年4月29日 下午10:50:10
 * @version V1.0.0
 */
public class ConfigBean implements Serializable
{
    /**
     * {变量说明}
     */
    private static final long serialVersionUID = 1L;

    // private static final Log LOGGER = LogFactory.getLog(ConfigBean.class);

    public ConfigBean()
    {

    }

    public ConfigBean(String englishName, String chineseDescription)
    {
        this.englishName = englishName;
        this.chineseDescription = chineseDescription;
    }

    private String englishName;

    private String chineseDescription;

    public String getEnglishName()
    {
        return englishName;
    }

    public void setEnglishName(String englishName)
    {
        this.englishName = englishName;
    }

    public String getChineseDescription()
    {
        return chineseDescription;
    }

    public void setChineseDescription(String chineseDescription)
    {
        this.chineseDescription = chineseDescription;
    }

    @Override
    public String toString()
    {
        return "ConfigBean [englishName=" + englishName + ", chineseDescription=" + chineseDescription + "]";
    }

}
