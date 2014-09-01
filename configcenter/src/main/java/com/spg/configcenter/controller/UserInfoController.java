
/**
 * 
 */
package com.spg.configcenter.controller;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spg.configcenter.bean.SessionInfo;
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
@RequestMapping("/userInfoManager")
public class UserInfoController
{
	private static final Log LOGGER = LogFactory
			.getLog(UserInfoController.class);
	
	@RequestMapping(value="/getOnlineUser", method=RequestMethod.GET)
	public ModelAndView getOnlineUserSession()
	{
		LOGGER.debug("enter function");
		ModelAndView view = new ModelAndView("/push/onlineusers");
		
		ChannelGroup clients = PushServerHandler.clients;
		String [] strs = {""};
		if (strs instanceof String[])
		{
		    
		}
		List<SessionInfo> onlineUsers = new ArrayList<SessionInfo>();
		SessionInfo sessionInfo = null;
		
		for (Channel channel : clients)
		{
			sessionInfo = new SessionInfo();
			sessionInfo.setIp(channel.remoteAddress().toString());
			onlineUsers.add(sessionInfo);
		}
		
		return view;
	}
	
}

