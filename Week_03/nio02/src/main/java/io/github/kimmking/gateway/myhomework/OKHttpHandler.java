package io.github.kimmking.gateway.myhomework;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author Mr.xiao
 * @create 2021-01-31 11:44
 */
public class OKHttpHandler {
    private List<String> backendUrls;
    private OkHttpClient client;
    private ExecutorService proxyService;
    HttpEndpointRouter router = new RandomHttpEndpointRouter();
    public OKHttpHandler(List<String> proxys){
        this.backendUrls=proxys;
        this.client = new OkHttpClient();
        this.proxyService= Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 2);
    }
    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter){
        FullHttpResponse response = null;
        try {
            //1.路由
            String backendUrl = router.route(this.backendUrls);
            final String url = backendUrl + fullHttpRequest.uri();

            //2.request filter
            filter.filter(fullHttpRequest, ctx);

            //3.请求
            String responseString = doGet(fullHttpRequest, url);

            //4.response filter
            byte[] body = responseString.getBytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", body.length);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }
    private String doGet(final FullHttpRequest inbound, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("hello", inbound.headers().get("hello"))
                .addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}
