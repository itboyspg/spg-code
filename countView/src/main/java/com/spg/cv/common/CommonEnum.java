package com.spg.cv.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spg.common.enumutil.EnumInterface;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月9日 下午9:24:28
 * @version V1.0.0
 */
public class CommonEnum
{
    private static final Log LOGGER = LogFactory.getLog(CommonEnum.class);

    public enum DataType implements EnumInterface
    {
        PAGE_VIEW(1, "PageView", "PageViewConfig"), BUTTON_CLICK_COUNT(2, "ButtonClick", "ButtonClickConfig"), LINK_CLICK_COUNT(
                3, "LinkClick", "LinkClickConfig"), USER_ACTIVE(3, "UserActive", "UserActiveConfig");

        private int code;
        private String name;
        private String configName;

        private DataType(int code, String name, String configName)
        {
            this.code = code;
            this.name = name;
            this.configName = configName;
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
        
        public String getConfigName()
        {
            return this.configName;
        }

        public static DataType getEnumByCode(int code)
        {
            LOGGER.debug(String.format("enter function,  %d", code));
            for (DataType type : DataType.values())
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
