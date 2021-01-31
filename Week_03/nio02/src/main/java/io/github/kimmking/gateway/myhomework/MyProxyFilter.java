package io.github.kimmking.gateway.myhomework;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @author Mr.xiao
 * @create 2021-01-31 9:27
 */
public class MyProxyFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        System.out.println(" filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx)接收到的请求,url: " + uri);
        if (uri.startsWith("/hello")) {
            // 放过
        } else {
            throw new RuntimeException("不支持的uri:" + uri);
        }
        HttpHeaders headers = fullRequest.headers();
        if (headers == null) {
            headers = new DefaultHttpHeaders();
        }
        headers.add("proxy-tag", this.getClass().getSimpleName());
    }

}
