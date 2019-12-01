package muserver.auth.server.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import muserver.auth.server.configs.AuthServerProperties;
import muserver.server.base.BasePacketHandler;

public class ServerConnectHandler extends BasePacketHandler {
    private final AuthServerProperties props;

    public ServerConnectHandler(AuthServerProperties props) {
        this.props = props;
    }

    @Override
    public void send(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        int serverCode = byteBuf.readUnsignedShort();

        AuthServerProperties.Server server = props.getServers().get(serverCode);

        int size = 22;

        ByteBuf directBuffer = Unpooled.directBuffer(size);

        directBuffer.writeByte(0xC1);
        directBuffer.writeByte(size);
        directBuffer.writeByte(0xF4);
        directBuffer.writeByte(3);

        int i = 0;

        for (byte val : server.getIp().getBytes()) {
            directBuffer.writeByte(val);
            i++;
        }

        directBuffer.writeByte(0);

        int n = 16 - i - 1;

        while (n > 0) {
            directBuffer.writeByte(0xFE);
            n--;
        }

        directBuffer.writeShortLE(server.getPort());

        super.send(ctx, directBuffer);
    }
}