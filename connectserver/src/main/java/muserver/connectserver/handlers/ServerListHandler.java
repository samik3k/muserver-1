package muserver.connectserver.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import muserver.connectserver.configs.ConnectServerProperties;
import muserver.serverbase.BasePacketHandler;

public class ServerListHandler extends BasePacketHandler {
    private final ConnectServerProperties props;

    public ServerListHandler(ConnectServerProperties props) {
        this.props = props;
    }

    @Override
    public void send(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        int size = (7 + (props.getServers().size() * 4));

        ByteBuf directBuffer = Unpooled.directBuffer(size);

        directBuffer.writeByte(0xC2);
        directBuffer.writeShort(size);
        directBuffer.writeByte(0xF4);
        directBuffer.writeByte(0x06);
        directBuffer.writeShort(props.getServers().size());

        for (Integer key : props.getServers().keySet()) {
            directBuffer.writeByte(key).writeByte(65 / 100 * 100).writeByte(50).writeByte(0xCC);
        }

        super.send(ctx, directBuffer);
    }
}