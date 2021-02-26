package com.netty.demo.demo.client;


import com.alibaba.fastjson.JSON;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/20 17:45
 */
public class SocketEchoClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception{
        if (msg instanceof String){
            String str = (String) msg;
            System.out.println("recv form server :" + str);
            return;
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext context) throws Exception{
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    while (scanner.hasNextLine()){
                        String line = scanner.nextLine();
                        if (line.startsWith("##stop")){
                            context.close();
                            break;
                        }
                        line += System.getProperty("line.separator");
                        System.out.println(line);
                        context.writeAndFlush(Unpooled.copiedBuffer(line.getBytes()));
                    }
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
            context.close();
        }
    }

}
