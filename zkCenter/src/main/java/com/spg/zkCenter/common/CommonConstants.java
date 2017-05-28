package com.spg.zkCenter.common;

import java.util.Date;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

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
            SerializerFeature.WriteMapNullValue, SerializerFeature.UseISO8601DateFormat };

    public static final Feature[] DESERIALIZER_FEATURES =
    { Feature.DisableCircularReferenceDetect };

    public static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig();

    static
    {
        SERIALIZE_CONFIG.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

}
