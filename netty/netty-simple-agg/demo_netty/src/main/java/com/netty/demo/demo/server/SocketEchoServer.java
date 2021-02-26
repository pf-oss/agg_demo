package com.netty.demo.demo.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/20 16:34
 */
public class SocketEchoServer {

    private static int PORT = 9999;

    private static void startServer(int port){
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 256)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("lineDecodeHandler", new LineBasedFrameDecoder(1024, false, false))
                                    .addLast("stringDecoder", new StringDecoder())
                                    .addLast("heartBeatHandler", new IdleStateHandler(0, 0, 30))
                                    .addLast("echoHandler", new SocketEchoServerHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("------ server bind success on port:" + PORT + "------");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args){
        SocketEchoServer.startServer(PORT);
    }


}
