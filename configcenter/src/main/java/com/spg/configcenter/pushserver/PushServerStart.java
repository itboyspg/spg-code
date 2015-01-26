/**
 * 
 */
package com.spg.configcenter.pushserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：netty
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年7月7日 上午11:41:00
 * 
 * @version V1.0.0
 * 
 */
public class PushServerStart
{

    private static final Log LOGGER = LogFactory.getLog(PushServerStart.class);

    private int port = 8099;

    public void startServer()
    {
        // netty启动会阻塞主线程，所以此处采用异步线程通过spring容器启动
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                LOGGER.info("start push server!");
                EventLoopGroup bossGroup = new NioEventLoopGroup(1);
                EventLoopGroup workerGroup = new NioEventLoopGroup(100);

                try
                {
                    ServerBootstrap bootstrap = new ServerBootstrap();
                    bootstrap.group(bossGroup, workerGroup);
                    bootstrap.channel(NioServerSocketChannel.class);
                    bootstrap.handler(new LoggingHandler(LogLevel.INFO));
                    bootstrap.childHandler(new PushServerInitializer());
                    bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

                    bootstrap.bind(port).sync().channel().closeFuture().sync();
                } catch (InterruptedException e)
                {
                    LOGGER.error("start push server error!", e);
                } finally
                {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void setPort(int port)
    {
        this.port = port;
    }

}
