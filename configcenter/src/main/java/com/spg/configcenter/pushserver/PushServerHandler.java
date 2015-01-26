/**
 * 
 */
package com.spg.configcenter.pushserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spg.configcenter.bean.MessageInfo;
import com.spg.configcenter.bean.UserInfo;

/**
 * 项目名称：netty
 * 
 * @description:
 * 
 * @author Administrator
 * 
 * @create_time：2014年7月7日 上午9:04:00
 * 
 * @version V1.0.0
 * 
 */
public class PushServerHandler extends ChannelInboundHandlerAdapter
{

    private static final Log LOGGER = LogFactory.getLog(PushServerHandler.class);

    // 存储所有客户端(此处必须为static修饰)
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static Map<String, UserInfo> userChannels = new ConcurrentHashMap<String, UserInfo>();

    /**
     * 
     * @param ctx
     * @throws Exception
     * 
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
     * 
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        LOGGER.debug("enter function");
        clients.add(ctx.channel());
        LOGGER.debug("exit function");
    }

    /**
     * @Description:
     * 
     * @param ctx
     * @param msg
     * @throws Exception
     * 
     * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext,
     *      java.lang.Object)
     * 
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        LOGGER.debug("enter function");
        if (msg instanceof MessageInfo)
        {
            MessageInfo messageInfo = (MessageInfo) msg;
            System.out.println("receive client reqeust:" + messageInfo);

            // 首次链接
            if (messageInfo.getUserInfo() != null)
            {
                messageInfo.getUserInfo().setUserChannel(ctx.channel());
                LOGGER.debug("at function, " + ctx.channel().remoteAddress().toString());
                userChannels.put(ctx.channel().remoteAddress().toString(), messageInfo.getUserInfo());

                // 给客户端回复消息
                MessageInfo response = new MessageInfo();
                response.setMessage("hello hero, Congratulations on your successful connection!");
                response.setMessageId(UUID.randomUUID().toString().replaceAll("-", ""));
                ctx.writeAndFlush(response);
            }

            // 如果为IM消息
            if (StringUtils.isNotEmpty(messageInfo.getToId()))
            {
                userChannels.get(messageInfo.getToId()).getUserChannel().writeAndFlush(msg);
            }

        }
        LOGGER.debug("exit function");
    }

    /**
     * @Description:
     * 
     * @param ctx
     * @param cause
     * @throws Exception
     * 
     * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext,
     *      java.lang.Throwable)
     * 
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        LOGGER.debug("enter function, deconnectIP:" + ctx.channel().remoteAddress().toString());
        cause.printStackTrace();
        // 从缓存移除在线用户数据
        userChannels.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        LOGGER.debug("exit function");
    }
}
