package com.spg.zkCenter.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2017年1月8日 下午1:25:40
 * @version V1.0.0
 */
public class CommonEnum
{
    private static final Log LOGGER = LogFactory.getLog(CommonEnum.class);

    public enum ConfigClassfication implements EnumInterface
    {
        CONFIG_MANAGEMENT(1, "ConfigManagement"), CONFIG_MGR_ACCESS_SYSTEM(2, "ConfigMgrAccessSystem"), BURIAL_POINT(3, "BurialPoint");

        int code;
        String name;

        ConfigClassfication(int code, String name)
        {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode()
        {
            return this.code;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public static ConfigClassfication getEnumByCode(int code)
        {
            LOGGER.debug(String.format("enter function,  %d", code));
            for (ConfigClassfication type : ConfigClassfication.values())
            {
                if (type.getCode() == code)
                {
                    return type;
                }
            }
            return null;
        }
    }
}
