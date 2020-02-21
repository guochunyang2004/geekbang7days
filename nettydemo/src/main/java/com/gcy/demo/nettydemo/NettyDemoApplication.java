package com.gcy.demo.nettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class NettyDemoApplication {
    public static void main(String[] args) {
        System.out.println("NettyDemoApplication");
    }


    public static void nettyDemo(){
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
       // final EchoServerHandler
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup,workerGroup);//.channel()
        }
        catch (Exception ex){

        }
    }
}