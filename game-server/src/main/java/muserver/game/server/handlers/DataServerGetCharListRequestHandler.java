package muserver.game.server.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import muserver.server.base.BasePacketHandler;
import muserver.game.server.configs.GameServerProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataServerGetCharListRequestHandler extends BasePacketHandler {
 private static final Logger logger = LogManager.getLogger(DataServerGetCharListRequestHandler.class);

 private final GameServerProperties props;

 public DataServerGetCharListRequestHandler(GameServerProperties props) {
  this.props = props;
 }

 @Override
 public void send(ChannelHandlerContext ctx, ByteBuf byteBuf) {

  super.send(ctx, byteBuf);
 }
}