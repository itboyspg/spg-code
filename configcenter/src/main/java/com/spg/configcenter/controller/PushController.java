/**
 * 
 */
package com.spg.configcenter.controller;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spg.configcenter.bean.MessageInfo;
import com.spg.configcenter.manager.FixedThreadPoolExcetor;
import com.spg.configcenter.manager.PushMsgTask;
import com.spg.configcenter.pushserver.PushServerHandler;

/**
 * 项目名称：configcenter
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年9月3日 上午11:54:29
 * 
 * @version V1.0.0
 * 
 */
@Controller
@RequestMapping("/pushCtrl")
public class PushController extends BaseController
{

    private static final Log LOGGER = LogFactory.getLog(PushController.class);

    @RequestMapping(value = "/forwardPush")
    public ModelAndView forwardPush()
    {
        ModelAndView view = new ModelAndView("/push/pushmsg");
        return view;
    }

    /**
     * 推送消息，只是创建发起推送的任务，然后即返回
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pushRequest", method = RequestMethod.POST, produces =
    { "application/json; charset=utf-8" })
    public String pushMessageRequest(HttpServletRequest request)
    {
        LOGGER.debug("enter function");

        String title = request.getParameter("title");
        String message = request.getParameter("message");
        
        if (null == message || "".endsWith(message.trim()))
        {
            return buildDefaultFailedData("push message is null!");
        }

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessage(message);
        messageInfo.setTitle(title);
        
        ChannelGroup clients = PushServerHandler.clients;
        if (null != clients && clients.size() > 0)
        {
            FixedThreadPoolExcetor excetor = FixedThreadPoolExcetor.getInstance();
            PushMsgTask task = null;

            for (Channel channel : clients)
            {
                task = new PushMsgTask(channel, messageInfo);
                excetor.addTask(task);
            }
        }

        return buildSuccessData("推送请求发送成功!");
    }

}
