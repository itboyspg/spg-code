/**
 * 
 */
package com.spg.configcenter.manager;

import io.netty.channel.Channel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：configcenter
 * 
 * @description:推送消息task
 * 
 * @author spg
 * 
 * @create_time：2014年9月3日 上午8:52:25
 * 
 * @version V1.0.0
 * 
 */
public class PushMsgTask implements Runnable
{

    private static final Log LOGGER = LogFactory.getLog(PushMsgTask.class);

    private Channel channel;

    private Object message;

    public PushMsgTask(Channel channel, Object message)
    {
        this.channel = channel;
        this.message = message;
    }

    /**
     * @Description:
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        LOGGER.debug("enter function");
        channel.writeAndFlush(message);
        LOGGER.debug("exit function");
    }

}
