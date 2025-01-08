package com.hbm.packet.toclient;

import com.hbm.main.MainRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;

import java.util.function.Supplier;

public class AuxParticlePacketNT {

	private final CompoundTag nbt;

	public AuxParticlePacketNT(CompoundTag nbt) {
		this.nbt = nbt;
	}

	public AuxParticlePacketNT(FriendlyByteBuf buf) {
		this.nbt = buf.readNbt();
	}

	public AuxParticlePacketNT(CompoundTag data, double posX, double posY, double posZ) {

	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeNbt(nbt);
	}

	public boolean handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if (Minecraft.getInstance().level == null) {
				return;
			}

			if (nbt != null) {
				MainRegistry.proxy.effectNT(nbt);
			}
		});
		ctx.get().setPacketHandled(true);
		return true;
	}

	public static AuxParticlePacketNT from(FriendlyByteBuf buf) {
		return new AuxParticlePacketNT(buf.readNbt());
	}
}