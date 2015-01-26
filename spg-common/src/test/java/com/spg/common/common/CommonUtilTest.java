
/**
 * 
 */
package com.spg.common.common;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

/**
 * 项目名称：spg-common
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年1月5日 下午2:55:04
 * @version V1.0.0
 *
 */
public class CommonUtilTest
{

    @Test
    public void testGetRealIpAddr()
    {
    }

    @Test
    public void testGenerateUrlShortCode()
    {
        String sourceUrl = "http://www.baidu.com/s?wd=%E6%8C%96%E8%B4%A2&rsv_spt=1&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=5&rsv_sug4=190&rsv_sug1=3&rsv_sug2=0&inputT=2302";
        
        System.out.println(Arrays.toString(CommonUtil.generateUrlShortCode(sourceUrl)));
    }
}

