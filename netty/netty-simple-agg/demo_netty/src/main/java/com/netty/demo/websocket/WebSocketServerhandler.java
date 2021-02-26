package com.netty.demo.websocket;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/20 15:07
 */
public class WebSocketServerhandler extends SimpleChannelInboundHandler {

    private static final Logger logger = Logger.getLogger(WebSocketServerhandler.class.getName());


    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        // 传统的Http接入
        if (o instanceof FullHttpRequest){
            handlerHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        }
        // WebSocket
        else if (o instanceof WebSocketFrame){
            handleWebSocketFrame(channelHandlerContext, (WebSocketFrame)o);
        }
    }

    private void handlerHttpRequest(ChannelHandlerContext context, FullHttpRequest request) throws Exception{
        System.out.println("handleHttpRequest");
        // 如果HTTP解码失败，返回HTTP异常
        if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))){
            sendHttpResponse(context, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }
        // 构造五首响应返回，本机测试
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
        handshaker = webSocketServerHandshakerFactory.newHandshaker(request);
        if (handshaker==null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
        }else {
            handshaker.handshake(context.channel(), request);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext context, WebSocketFrame frame){

        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame){
            handshaker.close(context.channel(), (CloseWebSocketFrame)frame.retain());
            return;
        }

        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame){
            context.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 本里程仅支持文本消息， 不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }

        // 返回应该消息
        String request = ((TextWebSocketFrame)frame).text();
        if (logger.isLoggable(Level.FINE)){
            logger.fine(String.format("%s received %s", context.channel(), request));
        }

        String result = (request + ", 欢迎使用Netty WebSocket服务, 现在时刻: " + new java.util.Date().toString());
        String resultOne = "";
        try {
            resultOne = new String( result.getBytes("GBK") , "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        context.channel().write(new TextWebSocketFrame(resultOne));
    }

    private static void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest request, FullHttpResponse response){
        // 返回应答给客户端
        if (response.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();

            try{
                Http2Headers http2Headers = HttpUtil.toHttp2Headers(response);
                http2Headers.addLong(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        // 如果是非Keep-Alive, 关闭连接
        ChannelFuture channelFuture = context.channel().writeAndFlush(response);
        try {
            Http2Headers http2Headers = HttpUtil.toHttp2Headers(response);
            Boolean keepAlive = Boolean.valueOf(http2Headers.get(HttpHeaderNames.CONTENT_LENGTH).toString());
            if (!keepAlive || response.status().code() != 200){
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) throws Exception{
        context.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception{
        cause.printStackTrace();
        context.close();
    }

}
