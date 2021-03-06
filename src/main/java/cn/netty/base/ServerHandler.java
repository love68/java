package cn.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress());
        System.out.println("channelActive is invoked");
//        InetSocketAddress address = (InetSocketAddress)ctx.channel().remoteAddress();
        InetSocketAddress address = (InetSocketAddress)ctx.channel().localAddress();
        int port = address.getPort();
        System.out.println("port = " + port);
    }

    /**
     * 收到数据时调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            InetSocketAddress address = (InetSocketAddress)ctx.channel().remoteAddress();
            int port = address.getPort();
            System.out.println("port = " + port);
            System.out.println("server channelRead");
            String result = (String)msg;
            result += "$$";
            System.out.println(result);
            ctx.writeAndFlush(result);//.addListener(ChannelFutureListener.CLOSE);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 抛出异常时调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    /**
     *  将buf转为string
     * @param buf
     * @return
     */
    public String convertByteBufToString(ByteBuf buf) {
        String str;
        if (buf.hasArray()) { // 处理堆缓冲区
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }

}
