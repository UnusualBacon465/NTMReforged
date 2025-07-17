package com.hbm.packet.toclient;

import com.hbm.main.MainRegistry;

import com.hbm.packet.threading.ThreadedPacket;
import com.hbm.util.BufferUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;

public class AuxParticlePacketNT extends ThreadedPacket {

	ByteBuf buffer;

	CompoundTag nbt;

	public AuxParticlePacketNT() { }

	public AuxParticlePacketNT(CompoundTag nbt, double x, double y, double z) {
		nbt.putDouble("posX", x);
		nbt.putDouble("posY", y);
		nbt.putDouble("posZ", z);
		this.nbt = nbt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = BufferUtil.readNBT(buf);
		this.buffer = buf;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		BufferUtil.writeNBT(buf, nbt);
	}

	public static class Handler implements IMessageHandler<AuxParticlePacketNT, IMessage> {

		@Override
		public IMessage onMessage(AuxParticlePacketNT m, MessageContext ctx) {

			if(Minecraft.getMinecraft().theWorld == null)
				return null;

			if(m.nbt != null)
				MainRegistry.proxy.effectNT(m.nbt);

			m.buffer.release();

			return null;
		}
	}
}
