package com.spg.cv.common;

import java.util.LinkedHashMap;
import java.util.Map;

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

    // 所要统计的数据维度
    public static final Map<String, String> COUNT_DATA_RELATION_MAP = new LinkedHashMap<String, String>();

    static
    {
        COUNT_DATA_RELATION_MAP.put("goodsMgr", "商品管理");
        COUNT_DATA_RELATION_MAP.put("userMgr", "用户管理");
        COUNT_DATA_RELATION_MAP.put("home", "主页");
        COUNT_DATA_RELATION_MAP.put("queryGoods", "查询商品");
        COUNT_DATA_RELATION_MAP.put("addGoods", "添加商品");
        COUNT_DATA_RELATION_MAP.put("addUser", "添加用户");
        COUNT_DATA_RELATION_MAP.put("queryUser", "查询用户");
        COUNT_DATA_RELATION_MAP.put("deleteUser", "删除用户");
        COUNT_DATA_RELATION_MAP.put("registUser", "注册用户");
        COUNT_DATA_RELATION_MAP.put("deleteGoods", "删除商品");
        COUNT_DATA_RELATION_MAP.put("updateGoods", "更新商品");
        COUNT_DATA_RELATION_MAP.put("updateUser", "更新用户");
    }
}
