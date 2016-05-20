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
        PAGE_VIEW(1, "PageView"), BUTTON_CLICK_COUNT(2, "ButtonClickCount"), LINK_CLICK_COUNT(3,
                "LinkClickCount"), USER_ACTIVE(3, "UserActive");

        private int code;
        private String name;

        private DataType(int code, String name)
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
