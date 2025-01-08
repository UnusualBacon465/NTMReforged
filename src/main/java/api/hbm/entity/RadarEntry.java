package api.hbm.entity;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@SuppressWarnings("'level' has private access in net.minecraft.world.entity.Entity")

public class RadarEntry {

	public String unlocalizedName;
	public int blipLevel;
	public int posX;
	public int posY;
	public int posZ;
	public ResourceKey<Level> dimension;
	public int entityID;
	public boolean redstone;

	// Default constructor for packets
	public RadarEntry() { }

	public RadarEntry(String name, int level, int x, int y, int z, ResourceKey<Level> dim, int entityID, boolean redstone) {
		this.unlocalizedName = name;
		this.blipLevel = level;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.dimension = dim;
		this.entityID = entityID;
		this.redstone = redstone;
	}

	public RadarEntry(IRadarDetectable detectable, Entity entity, boolean redstone) {
		this(detectable.getUnlocalizedName(),
				detectable.getBlipLevel(),
				(int) Math.floor(entity.getX()),
				(int) Math.floor(entity.getY()),
				(int) Math.floor(entity.getZ()),
				entity.getCommandSenderWorld().dimension(), // Accessing dimension via public field
				entity.getId(),
				redstone);
	}

	public RadarEntry(IRadarDetectable detectable, Entity entity) {
		this(detectable.getTargetType().name,
				detectable.getTargetType().ordinal(),
				(int) Math.floor(entity.getX()),
				(int) Math.floor(entity.getY()),
				(int) Math.floor(entity.getZ()),
				entity.getCommandSenderWorld().dimension(), // Accessing dimension via public field
				entity.getId(),
				entity.getDeltaMovement().y < 0);
	}

	public RadarEntry(Player player) {
		this(player.getName().getString(),
				IRadarDetectableNT.PLAYER,
				(int) Math.floor(player.getX()),
				(int) Math.floor(player.getY()),
				(int) Math.floor(player.getZ()),
				player.getCommandSenderWorld().dimension(), // Accessing dimension via public field
				player.getId(),
				true);
	}

	public void fromBytes(FriendlyByteBuf buf) {
		this.unlocalizedName = buf.readUtf(32767); // Maximum string length
		this.blipLevel = buf.readShort();
		this.posX = buf.readInt();
		this.posY = buf.readInt();
		this.posZ = buf.readInt();
		this.dimension = ResourceKey.create((ResourceKey<? extends Registry<Level>>) Level.RESOURCE_KEY_CODEC, buf.readResourceLocation());
		this.entityID = buf.readInt();
		this.redstone = buf.readBoolean();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUtf(this.unlocalizedName);
		buf.writeShort(this.blipLevel);
		buf.writeInt(this.posX);
		buf.writeInt(this.posY);
		buf.writeInt(this.posZ);
		buf.writeResourceLocation(this.dimension.location());
		buf.writeInt(this.entityID);
		buf.writeBoolean(this.redstone);
	}
}