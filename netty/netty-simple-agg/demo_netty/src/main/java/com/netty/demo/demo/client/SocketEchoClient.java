package com.netty.demo.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/20 17:26
 */
public class SocketEchoClient {

    private static String LOCAL_HOST = "127.0.0.1";
    private static int SERVER_PORT = 9999;

    public static void main(String[] args){
        SocketEchoClient.startClient(SERVER_PORT);
    }


    private static void startClient(int serverPort){
        NioEventLoopGroup group = new NioEventLoopGroup(1);

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast("lineDecodeHandler", new LineBasedFrameDecoder(1024, false, false))
                                    .addLast("stringDecoder", new StringDecoder())
                                    .addLast("echoClientHandler", new SocketEchoClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(LOCAL_HOST, serverPort).sync();
            System.out.println("client connect to server success! host:" + LOCAL_HOST + ":" + SERVER_PORT);
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
