package com.spg.cv.common;

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

    // 淘宝IP地址解析，形如http://ip.taobao.com/service/getIpInfo.php?ip=101.233.200.196
    // 查询频率限制 10qps
    public static final String TAOBAO_IP_ANALYSE_URL = "http://ip.taobao.com/service/getIpInfo.php";
}
