package muserver.dataserver.channels;

import base.BasePacketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import muserver.dataserver.configs.DataServerProperties;
import muserver.dataserver.handlers.GJPCharacterListRequestCS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DataServerChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {
 private static final Logger logger = LoggerFactory.getLogger(DataServerChannelHandler.class);

 private final Map<Integer, BasePacketHandler> packets;

 public DataServerChannelHandler(DataServerProperties props) {
  packets = new HashMap<>();

  packets.put(0x01, new GJPCharacterListRequestCS(props));

 }

 @Override
 public void channelActive(ChannelHandlerContext ctx) {
  if (ctx.channel().remoteAddress() != null) {
   logger.info("Accepted a client connection from remote address: {}", ctx.channel().remoteAddress().toString());
  }
 }

 @Override
 public void channelInactive(ChannelHandlerContext ctx) {
  if (ctx.channel().remoteAddress() != null) {
   logger.info("Client from remote address: {} has interrupted connection", ctx.channel().remoteAddress().toString());
  }
 }

 @Override
 public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
  logger.error(cause.getMessage(), cause);
  ctx.close();
 }

 @Override
 public void channelReadComplete(ChannelHandlerContext ctx) {
  ctx.flush();
 }

 @Override
 protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) {
  logger.info("RECEIVED PACKET\n{}", ByteBufUtil.prettyHexDump(byteBuf));
 }
}