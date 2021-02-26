package com.netty.demo.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/20 14:28
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest){
            HttpRequest request = (HttpRequest) httpObject;
            System.out.println("接收到请求: " + request.method() + ",url=" + request.uri());

            // 设置返回内容
            ByteBuf content = Unpooled.copiedBuffer("Hello World\n", CharsetUtil.UTF_8);

            // 创建响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes() + "");
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
