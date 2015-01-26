/**
 * 
 */
package com.spg.configcenter.controller;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spg.configcenter.bean.SessionInfo;
import com.spg.configcenter.bean.UserInfo;
import com.spg.configcenter.pushserver.PushServerHandler;

/**
 * 项目名称：configcenter
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年7月14日 下午9:04:09
 * 
 * @version V1.0.0
 * 
 */
@Controller
@RequestMapping("/userCtrl")
public class UserInfoController
{
    private static final Log LOGGER = LogFactory.getLog(UserInfoController.class);

    @RequestMapping(value = "/queryOnlineUser", method = RequestMethod.GET)
    public ModelAndView queryOnlineUserSession(HttpServletRequest request)
    {
        LOGGER.debug("enter function");
        ModelAndView view = new ModelAndView("/push/onlineuser");

        Map<String, UserInfo> clients = PushServerHandler.userChannels;

        List<SessionInfo> onlineUsers = new ArrayList<SessionInfo>();
        SessionInfo sessionInfo = null;

        Entry<String, UserInfo> entry = null;
        for (Iterator<Entry<String, UserInfo>> iterator = clients.entrySet().iterator(); iterator.hasNext();)
        {
            entry = iterator.next();
            sessionInfo = new SessionInfo();
            sessionInfo.setIp(entry.getKey());
            sessionInfo.setChannelId(entry.getValue().getId().toString());
            sessionInfo.setAlive(entry.getValue().getUserChannel().isActive());
            
            onlineUsers.add(sessionInfo);
        }
        // TODO for test
        sessionInfo = new SessionInfo();
        sessionInfo.setIp("test-user");
        sessionInfo.setChannelId("88888888");
        onlineUsers.add(sessionInfo);
        view.addObject("users", onlineUsers);
        LOGGER.debug("exit function, " + view);
        return view;
    }

    @RequestMapping(value = "/alivedUser", method = RequestMethod.GET)
    public ModelAndView queryAlivedUser(HttpServletRequest request)
    {
        LOGGER.debug("enter function");
        ModelAndView view = new ModelAndView("/push/aliveduser");

        LOGGER.debug("exit function, " + view);
        return view;
    }

}
