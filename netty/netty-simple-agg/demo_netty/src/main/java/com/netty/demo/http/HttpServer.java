package com.netty.demo.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/20 14:15
 */
public class HttpServer {

    public static void main(String[] args) {
        HttpServer.startServer(9999);
    }

    public static void startServer(int port) {
        // 负责接收客户端连接
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        // 处理连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();

                            // 负责http 请求编码解码
                            channelPipeline.addLast(new HttpServerCodec());
                            // 实际处理请求
                            channelPipeline.addLast(new HttpRequestHandler());
                        }
                    });

            // 绑定端口号
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
