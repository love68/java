package cn.netty.senior;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static void main(String[] args) throws Exception{
        EventLoopGroup workerGroup = new NioEventLoopGroup();//负责处理任务

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());//添加处理器
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());//添加处理器
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);

        Request request = new Request();
        request.setName("xxxx");
        request.setAge(20);
        ChannelFuture f = bootstrap.connect("127.0.0.1",8888).sync();
        f.channel().writeAndFlush(request);


        f.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();

    }
}
