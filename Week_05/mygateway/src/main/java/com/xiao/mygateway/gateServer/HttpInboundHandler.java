package com.xiao.mygateway.gateServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xiao
 * @create 2021-03-07 17:07
 */
@Component
public class HttpInboundHandler extends ChannelInboundHandlerAdapter
{
    @Autowired
    private Logger logger;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request=(FullHttpRequest) msg;
        logger.debug(((FullHttpRequest) msg).uri());
        super.channelRead(ctx, msg);
    }
}
