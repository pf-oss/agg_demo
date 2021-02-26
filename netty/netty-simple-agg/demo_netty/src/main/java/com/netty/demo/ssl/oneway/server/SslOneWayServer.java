package com.netty.demo.ssl.oneway.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SslOneWayServer {

	private static  int PORT = 9999;

	public void run(int port) throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new SslOneWayServerInitializer());
			
			ChannelFuture cf = b.bind(port).sync();
			
			cf.channel().closeFuture().sync();
			
		}finally{
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		new SslOneWayServer().run(PORT);

	}

}
