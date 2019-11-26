package muserver.connectserver.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import muserver.baseserver.BasePacketHandler;

public class AcceptClientHandler extends BasePacketHandler {
 @Override
 public void send(ChannelHandlerContext ctx, ByteBuf byteBuf) {
  byteBuf.writeByte(0xC1).writeByte(0x4).writeByte(0x0).writeByte(0x1);
  super.send(ctx, byteBuf);
 }
}
