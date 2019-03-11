package cn.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive is invoked");
        ctx.writeAndFlush(Unpooled.copiedBuffer("连接成功".getBytes()));
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
            System.out.println(address.getPort());
            System.out.println(address.getAddress());
            //doing else something
            ByteBuf buf = (ByteBuf) msg;
            String result = convertByteBufToString(buf);
            System.out.println("收到客户端的信息:"+result);
            System.out.println("开始响应");
            //给客户端响应完成之后，关闭该客户端连接
            ctx.writeAndFlush(Unpooled.copiedBuffer(result.getBytes())).addListener(ChannelFutureListener.CLOSE);
            System.out.println("响应结束");
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
