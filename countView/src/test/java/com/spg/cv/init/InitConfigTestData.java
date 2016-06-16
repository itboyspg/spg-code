package com.spg.cv.init;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.common.RedisPoolUtil;
import com.spg.cv.dao.RedisListAPIUtil;
import com.spg.cv.po.PVBean;

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
    List<PVBean> pvConfig = new ArrayList<PVBean>();
    // 链接点击配置
    List<PVBean> linkClickConfig = new ArrayList<PVBean>();
    // 按钮点击配置
    List<PVBean> buttonClickConfig = new ArrayList<PVBean>();

    @Before
    public void setUp()
    {
        // 清空原来配置数据
        RedisPoolUtil.getJedis().del(DataType.PAGE_VIEW.getConfigName());
        RedisPoolUtil.getJedis().del(DataType.LINK_CLICK_COUNT.getConfigName());
        RedisPoolUtil.getJedis().del(DataType.BUTTON_CLICK_COUNT.getConfigName());
    }

    @Test
    public void testInitPageViewConfig()
    {
        pvConfig.add(new PVBean("login", "登陆页"));
        pvConfig.add(new PVBean("home", "首页"));
        pvConfig.add(new PVBean("regist", "注册页"));
        pvConfig.add(new PVBean("userList", "用户列表页"));
        pvConfig.add(new PVBean("userManagement", "用户管理页"));
        pvConfig.add(new PVBean("configManagement", "配置管理页"));
        pvConfig.add(new PVBean("goodsList", "商品列表页"));
        pvConfig.add(new PVBean("goodsManagement", "商品管理页"));
        for (PVBean pv : pvConfig)
        {
            RedisListAPIUtil.addToList(DataType.PAGE_VIEW.getConfigName(), JSON.toJSONString(pv));
        }
    }

    @Test
    public void testLinkClickConfig()
    {
        linkClickConfig.add(new PVBean("vip-mail", "VIP邮箱"));
        linkClickConfig.add(new PVBean("google-mail", "谷歌邮箱"));
        linkClickConfig.add(new PVBean("163-mail", "163邮箱"));
        linkClickConfig.add(new PVBean("qq-mail", "QQ邮箱"));
        linkClickConfig.add(new PVBean("126-mail", "126邮箱"));
        linkClickConfig.add(new PVBean("my-mail", "我的邮箱"));
        linkClickConfig.add(new PVBean("Google", "谷歌搜索"));
        linkClickConfig.add(new PVBean("360", "360搜索"));
        linkClickConfig.add(new PVBean("Yahoo", "雅虎搜索"));
        for (PVBean pv : linkClickConfig)
        {
            RedisListAPIUtil.addToList(DataType.LINK_CLICK_COUNT.getConfigName(), JSON.toJSONString(pv));
        }
    }

    @Test
    public void testButtonClickConfig()
    {
        buttonClickConfig.add(new PVBean("login", "登陆"));
        buttonClickConfig.add(new PVBean("regist", "注册"));
        buttonClickConfig.add(new PVBean("delete-user", "删除用户"));
        buttonClickConfig.add(new PVBean("add-user", "添加用户"));
        buttonClickConfig.add(new PVBean("update-user", "修改用户"));
        buttonClickConfig.add(new PVBean("query-user", "查询用户"));
        buttonClickConfig.add(new PVBean("query-goods", "查询商品"));
        buttonClickConfig.add(new PVBean("add-goods", "添加商品"));
        buttonClickConfig.add(new PVBean("buy-goods", "购买商品"));
        buttonClickConfig.add(new PVBean("add-goods-cart", "添加购物车"));
        for (PVBean pv : buttonClickConfig)
        {
            RedisListAPIUtil.addToList(DataType.BUTTON_CLICK_COUNT.getConfigName(), JSON.toJSONString(pv));
        }
    }
}
