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
    
//    // 全国省份
//    public static Map<String, String> CN_PROVINCE = new HashMap<String, String>();
//    
//    static
//    {
//        CN_PROVINCE.put("beijing", "北京市");
//        CN_PROVINCE.put("tianjin", "天津市");
//        CN_PROVINCE.put("shanghai", "上海市");
//        CN_PROVINCE.put("chongqing", "重庆市");
//        
//        CN_PROVINCE.put("hebei", "河北省");
//        CN_PROVINCE.put("shanxi", "山西省");
//        CN_PROVINCE.put("liaoning", "辽宁省");
//        CN_PROVINCE.put("jilin", "吉林省");
//        CN_PROVINCE.put("heilongjiang", "黑龙江省");
//        CN_PROVINCE.put("jiangsu", "江苏省");
//        CN_PROVINCE.put("zhejiang", "浙江省");
//        CN_PROVINCE.put("anhui", "安徽省");
//        CN_PROVINCE.put("fujian", "福建省");
//        CN_PROVINCE.put("jiangxi", "江西省");
//        CN_PROVINCE.put("shandong", "山东省");
//        CN_PROVINCE.put("henan", "河南省");
//        CN_PROVINCE.put("hubei", "湖北省");
//        CN_PROVINCE.put("hunan", "湖南省");
//        CN_PROVINCE.put("guangdong", "广东省");
//        CN_PROVINCE.put("hainan", "海南省");
//        CN_PROVINCE.put("sichuan", "四川省");
//        CN_PROVINCE.put("guizhou", "贵州省");
//        CN_PROVINCE.put("yunnan", "云南省");
//        CN_PROVINCE.put("shanxi-bingmayong", "陕西省");
//        CN_PROVINCE.put("gansu", "甘肃省");
//        CN_PROVINCE.put("qinghai", "青海省");
//        CN_PROVINCE.put("taiwan", "台湾省");
//        
//        CN_PROVINCE.put("neimenggu", "内蒙古自治区");
//        CN_PROVINCE.put("guangxi", "广西壮族自治区");
//        CN_PROVINCE.put("xizang", "西藏自治区");
//        CN_PROVINCE.put("ningxia", "宁夏回族自治区");
//        CN_PROVINCE.put("xinjiang", "新疆维吾尔自治区");
//        
//        CN_PROVINCE.put("xianggang", "香港特别行政区");
//        CN_PROVINCE.put("aomen", "澳门特别行政区");
//    }
}
