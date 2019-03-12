package cn.netty.senior;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    public static void main(String[] args) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 负责处理来自客户端的链接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//负责处理任务

        ServerBootstrap bootstrap = new ServerBootstrap();//配置类
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());//添加处理器
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());//添加处理器
                        socketChannel.pipeline().addLast(new ServerHandler());//添加处理器
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture future = bootstrap.bind(8888).sync();
        future.channel().closeFuture().sync();//阻塞线程

        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();

    }
}
