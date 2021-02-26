package com.netty.demo.demo.server;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/20 16:54
 */
public class SocketEchoServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception{
        InetSocketAddress address = (InetSocketAddress)context.channel().remoteAddress();
        System.out.println("--- client active: host = " + address.getHostName() + ", port = " + address.getPort());
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception{
        InetSocketAddress address = (InetSocketAddress) context.channel().remoteAddress();
        System.out.println("--- client inactive：host=" + address.getHostName() + ", port=" + address.getPort() + "-----");
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception{
        System.out.println("system out....." + JSON.toJSONString(context));

        if (msg instanceof String){
            String string = (String) msg;
            context.writeAndFlush(Unpooled.copiedBuffer(string.getBytes()));
            System.out.println("recv form client: " + string);
            return;
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object obj) throws Exception{
        if (obj instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) obj;

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());

            switch (idleStateEvent.state()){
                case ALL_IDLE:
                    System.out.println(date + ":read and write timeOut!");
                    break;
                case READER_IDLE:
                    System.out.println(date + ": read timeOut! ");
                    break;
                case WRITER_IDLE:
                    System.out.println(date + ": write timeOut!");
                    break;
            }



        }
    }

}
