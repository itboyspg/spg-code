package com.spg.zkCenter.common;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月7日 下午3:21:59
 * @version V1.0.0
 */
public final class CommonConstants
{
    // private static final Log LOGGER =
    // LogFactory.getLog(CommonConstants.class);

    private CommonConstants()
    {

    }

    public static final SerializerFeature[] SERIALIZER_FEATURES =
    { SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteMapNullValue };

    public static final Feature[] DESERIALIZER_FEATURES =
    { Feature.DisableCircularReferenceDetect };
    
}
