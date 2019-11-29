package muserver.game.server.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import muserver.base.server.BasePacketHandler;
import muserver.game.server.configs.GameServerProperties;

public class LiveClientHandler extends BasePacketHandler {
 private final GameServerProperties props;

 public LiveClientHandler(GameServerProperties props) {
  this.props = props;
 }

 @Override
 public void send(ChannelHandlerContext ctx, ByteBuf byteBuf) {
  byteBuf.readerIndex(3);
  long time = byteBuf.readUnsignedInt();
  int attackSpeed = byteBuf.readUnsignedShort(), magicSpeed = byteBuf.readUnsignedShort();
//  logger.info("Live client -> Time: {} | Attack speed: {} | Magic speed {}", time, attackSpeed, magicSpeed);
 }
}