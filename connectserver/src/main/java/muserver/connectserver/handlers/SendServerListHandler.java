package muserver.connectserver.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import muserver.common.AbstractPacketHandler;
import muserver.common.objects.ConnectorServerConfigs;

public class SendServerListHandler extends AbstractPacketHandler {
    private final ConnectorServerConfigs configs;

    public SendServerListHandler(ConnectorServerConfigs configs) {
        this.configs = configs;
    }

    @Override
    public void send(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        int size = (7 + (configs.servers().size() * 4));

        ByteBuf directBuffer = Unpooled.directBuffer(size);

        directBuffer.writeByte(0xC2);
        directBuffer.writeShort(size);
        directBuffer.writeByte(0xF4);
        directBuffer.writeByte(0x06);
        directBuffer.writeShort(configs.servers().size());

        for (Integer key : configs.servers().keySet()) {
            directBuffer.writeByte(key).writeByte(65 / 100 * 100).writeByte(50).writeByte(0xCC);
        }

        super.send(ctx, directBuffer);
    }
}