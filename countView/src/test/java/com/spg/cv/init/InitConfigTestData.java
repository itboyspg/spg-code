package com.spg.cv.init;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.common.RedisPoolUtil;
import com.spg.cv.dao.RedisListAPIUtil;
import com.spg.cv.po.ConfigBean;

/**
 * 项目名称：countView
 *
 * @description: 初始化配置项数据
 * @author Wind-spg
 * @create_time：2016年6月16日 下午9:48:51
 * @version V1.0.0
 */
public class InitConfigTestData extends BaseTest
{
    // private static final Log LOGGER = LogFactory.getLog(InitTestData.class);
    // PV配置项
    List<ConfigBean> pvConfig = new ArrayList<ConfigBean>();
    // 链接点击配置
    List<ConfigBean> linkClickConfig = new ArrayList<ConfigBean>();
    // 按钮点击配置
    List<ConfigBean> buttonClickConfig = new ArrayList<ConfigBean>();

    @Before
    public void setUp()
    {
    }

    @Test
    public void testInitPageViewConfig()
    {
        // 清空原来配置数据
        Jedis jedis = RedisPoolUtil.getJedis();
        jedis.del(DataType.PAGE_VIEW.getConfigName());
        RedisPoolUtil.release(jedis);
        pvConfig.add(new ConfigBean("login", "登陆页"));
        pvConfig.add(new ConfigBean("home", "首页"));
        pvConfig.add(new ConfigBean("regist", "注册页"));
        pvConfig.add(new ConfigBean("userList", "用户列表页"));
        pvConfig.add(new ConfigBean("userManagement", "用户管理页"));
        pvConfig.add(new ConfigBean("configManagement", "配置管理页"));
        pvConfig.add(new ConfigBean("goodsList", "商品列表页"));
        pvConfig.add(new ConfigBean("goodsManagement", "商品管理页"));
        for (ConfigBean pv : pvConfig)
        {
            RedisListAPIUtil.addToList(DataType.PAGE_VIEW.getConfigName(), JSON.toJSONString(pv));
        }
    }

    @Test
    public void testLinkClickConfig()
    {
        Jedis jedis = RedisPoolUtil.getJedis();
        jedis.del(DataType.LINK_CLICK_COUNT.getConfigName());
        RedisPoolUtil.release(jedis);
        linkClickConfig.add(new ConfigBean("vip-mail", "VIP邮箱"));
        linkClickConfig.add(new ConfigBean("google-mail", "谷歌邮箱"));
        linkClickConfig.add(new ConfigBean("163-mail", "163邮箱"));
        linkClickConfig.add(new ConfigBean("qq-mail", "QQ邮箱"));
        linkClickConfig.add(new ConfigBean("126-mail", "126邮箱"));
        linkClickConfig.add(new ConfigBean("my-mail", "我的邮箱"));
        linkClickConfig.add(new ConfigBean("Google", "谷歌搜索"));
        linkClickConfig.add(new ConfigBean("360", "360搜索"));
        linkClickConfig.add(new ConfigBean("Yahoo", "雅虎搜索"));
        for (ConfigBean pv : linkClickConfig)
        {
            RedisListAPIUtil.addToList(DataType.LINK_CLICK_COUNT.getConfigName(), JSON.toJSONString(pv));
        }
    }

    @Test
    public void testButtonClickConfig()
    {
        Jedis jedis = RedisPoolUtil.getJedis();
        jedis.del(DataType.BUTTON_CLICK_COUNT.getConfigName());
        RedisPoolUtil.release(jedis);
        buttonClickConfig.add(new ConfigBean("login", "登陆"));
        buttonClickConfig.add(new ConfigBean("regist", "注册"));
        buttonClickConfig.add(new ConfigBean("delete-user", "删除用户"));
        buttonClickConfig.add(new ConfigBean("add-user", "添加用户"));
        buttonClickConfig.add(new ConfigBean("update-user", "修改用户"));
        buttonClickConfig.add(new ConfigBean("query-user", "查询用户"));
        buttonClickConfig.add(new ConfigBean("query-goods", "查询商品"));
        buttonClickConfig.add(new ConfigBean("add-goods", "添加商品"));
        buttonClickConfig.add(new ConfigBean("buy-goods", "购买商品"));
        buttonClickConfig.add(new ConfigBean("add-goods-cart", "添加购物车"));
        for (ConfigBean pv : buttonClickConfig)
        {
            RedisListAPIUtil.addToList(DataType.BUTTON_CLICK_COUNT.getConfigName(), JSON.toJSONString(pv));
        }
    }
}
