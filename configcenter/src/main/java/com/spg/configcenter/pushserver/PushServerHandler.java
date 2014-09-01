
/**
 * 
 */
package com.spg.configcenter.pushserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class PushServerHandler extends SimpleChannelInboundHandler<String>
{
	
	private static final Log LOGGER = LogFactory
			.getLog(PushServerHandler.class);
	
	// 存储所有客户端(此处必须为static修饰)
	public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	/**
	* 
	* @param ctx
	* @throws Exception
	*  
	* @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	*   
	*/
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception
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
	* @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	*   
	*/
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception
	{
		LOGGER.debug("enter function");
		// 给所有客户端发送收到的消息
		for (Channel channel : clients)
		{
			if (null != channel && !ctx.channel().equals(channel))
			{
				channel.writeAndFlush("[" + ctx.channel().remoteAddress() + " say] : " + msg + '\n');
			}
			else {
				channel.writeAndFlush("[you say] : " + msg + "\n");
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
	* @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	*   
	*/
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception
	{
		LOGGER.debug("enter function");
		cause.printStackTrace();
		ctx.close();
		LOGGER.debug("exit function");
	}
}

