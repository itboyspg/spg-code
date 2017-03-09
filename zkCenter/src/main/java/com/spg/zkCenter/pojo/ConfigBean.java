package com.spg.zkCenter.pojo;

import java.io.Serializable;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年12月4日 下午1:17:40
 * @version V1.0.0
 */
public class ConfigBean implements Serializable
{
    /**
     * {变量说明}
     */
    private static final long serialVersionUID = -4296063365450824067L;

    private String systemName;
    
    private String configName;

    private String configValue;

    private String configPath;

    private String remark;

    
    public String getSystemName()
    {
        return systemName;
    }

    public void setSystemName(String systemName)
    {
        this.systemName = systemName;
    }

    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    public String getConfigValue()
    {
        return configValue;
    }

    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }

    public String getConfigPath()
    {
        return configPath;
    }

    public void setConfigPath(String configPath)
    {
        this.configPath = configPath;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public String toString()
    {
        return "ConfigBean [systemName=" + systemName + ", configName=" + configName + ", configValue="
                + configValue + ", configPath=" + configPath + ", remark=" + remark + "]";
    }

}
