package muserver.connectserver.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import muserver.common.AbstractPacketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendServerListHandler extends AbstractPacketHandler {
    private static final Logger logger = LogManager.getLogger(SendServerListHandler.class);

    @Override
    public void send(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        int serversCount = 1;

        int size = (7 + (1 * 4));

        byteBuf.writeByte(0xC2);
        byteBuf.writeShortLE(size);
        byteBuf.writeByte(0xF4);
        byteBuf.writeByte(0x6);
        byteBuf.writeShortLE(serversCount);
        byteBuf.writeShortLE(0);
        byteBuf.writeShortLE(1);

        super.send(ctx, byteBuf);
    }
}