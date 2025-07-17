package com.hbm.network;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.NetworkChannel;
import net.neoforged.neoforge.network.registration.NetworkChannelBuilder;

public class NTMReforgedNetwork {
    public static final NetworkChannel CHANNEL = NetworkChannelBuilder
            .create(new ResourceLocation("ntmreforged", "main"), "1.0")
            .build();

    public static void registerPackets() {
        int id = 0;
        CHANNEL.register(id++, AuxParticlePacketNT.class, AuxParticlePacketNT::encode, AuxParticlePacketNT::decode, AuxParticlePacketNT::handle);
    }
}