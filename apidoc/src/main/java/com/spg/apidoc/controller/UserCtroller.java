package com.spg.apidoc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.spg.apidoc.po.UserInfo;
import com.spg.apidoc.service.UserService;
import com.spg.apidoc.vo.BaseResultVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 项目名称：apidoc
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年2月3日 上午10:37:04
 * @version V1.0.0
 *
 */
// @Api(basePath = "/", value = "userCtrl")
@Controller(value = "userCtrl")
public class UserCtroller extends BaseController
{

    private static final Log LOGGER = LogFactory.getLog(UserCtroller.class);

    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping(
            value = "addUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加用户", httpMethod = "POST", response = BaseResultVo.class, notes = "add user")
    public String addUser(@ApiParam(required = true, name = "postData", value = "用户信息json数据") @RequestParam(
            value = "postData") String postData, HttpServletRequest request)
    {
        LOGGER.debug(String.format("at function, %s", postData));
        if (null == postData || postData.isEmpty())
        {
            return super.buildFailedResultInfo(-1, "post data is empty!");
        }

        UserInfo user = JSON.parseObject(postData, UserInfo.class);
        int result = userService.addUser(user);
        return buildSuccessResultInfo(result);
    }

    @ResponseBody
    @RequestMapping(
            value = "queryUserById", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "根据用户ID查询用户信息")
    public String queryUserById(@ApiParam(required = true, name = "userId", value = "user id") @RequestParam(
            value = "userId") String userId, HttpServletRequest request)
    {
        UserInfo info = userService.queryUserById(Integer.parseInt(userId));

        return buildSuccessResultInfo(info);
    }
}
