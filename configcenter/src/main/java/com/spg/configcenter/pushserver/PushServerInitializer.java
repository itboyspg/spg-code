
/**
 * 
 */
package com.spg.configcenter.pushserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：netty
 *
 * @description:
 *
 * @author spg
 *
 * @create_time：2014年7月7日 上午11:16:23
 *
 * @version V1.0.0
 *
 */
public class PushServerInitializer extends ChannelInitializer<SocketChannel>
{
	
	private static final Log LOGGER = LogFactory
			.getLog(PushServerInitializer.class);

	/**
	* @Description:
	* 
	* @param ch
	* @throws Exception
	*  
	* @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	*   
	*/
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception
	{
		LOGGER.debug("enter function");
		
		ChannelPipeline pipeline = ch.pipeline();
//		pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//		pipeline.addLast(new StringEncoder());
//		pipeline.addLast(new StringDecoder());
		
		// 传递对象
		pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
		pipeline.addLast(new ObjectEncoder());
		
		pipeline.addLast(new PushServerHandler());
		
		LOGGER.debug("exit function");
	}
}

