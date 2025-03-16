package com.hbm.explosion;


import com.hbm.blocks.ModBlocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Random;



public class NukeEnvironmentalEffect {

	private static BlockPos pos;
	private static Object replacementBlock;

	@SubscribeEvent
	public static void onBlockPlace(net.neoforged.neoforge.event.level.ExplosionEvent.Detonate event) {
		if (!event.isExplosion() || !(event.getExplosion().getExploder() instanceof EntityPlayerMP)) {
			return;
		}


		Level level = event.getLevel();
		BlockPos pos = event.getExplosion().getPosition();

		int radius = 10; // Adjust as needed
		int j = 5; // Adjust as needed

		applyStandardAOE(level, pos.getX(), pos.getY(), pos.getZ(), radius, j);
	}

	public static void applyStandardAOE(Level level, int x, int y, int z, int r, int j) {

		int r2 = r * r;
		int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			int X = xx + x;
			int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				int Y = yy + y;
				int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					int Z = zz + z;
					int ZZ = YY + zz * zz;
					if (ZZ < r22 + rand.nextInt(j)) {
						applyStandardEffect(level, X, Y, Z);
					}
				}
			}
		}
	}

	public static void applyStandardEffect(Level level, int x, int y, int z) {
		int chance = 100;
		Block b = null;
		int meta = 0;

		BlockState blockState = level.getBlockState(pos);
		Block currentBlock = blockState.getBlock();

		if (currentBlock == Blocks.AIR)
			return;

		// Task done by fallout effect entity.
        /*if (in == Blocks.grass) {
            b = ModBlocks.waste_earth;
        } else */

		if (currentBlock == Blocks.SAND) {
			replacementBlock = rand.nextBoolean() ? ModBlocks.waste_trinitite : ModBlocks.waste_trinitite_red;
			chance = 20;

		} else if (currentBlock == Blocks.MYCELIUM) {
			b = ModBlocks.waste_mycelium;

			if (currentBlock == Blocks.OAK_LOG || currentBlock == Blocks.SPRUCE_LOG ||
					currentBlock == Blocks.BIRCH_LOG || currentBlock == Blocks.JUNGLE_LOG ||
					currentBlock == Blocks.ACACIA_LOG || currentBlock == Blocks.DARK_OAK_LOG ||
					currentBlock == Blocks.CHERRY_LOG || currentBlock == Blocks.MANGROVE_LOG ||
					currentBlock == Blocks.BAMBOO_BLOCK) {

				replacementBlock = ModBlocks.waste_log;
			}

			if (currentBlock == Blocks.OAK_PLANKS || currentBlock == Blocks.SPRUCE_PLANKS ||
					currentBlock == Blocks.BIRCH_PLANKS || currentBlock == Blocks.JUNGLE_PLANKS ||
					currentBlock == Blocks.ACACIA_PLANKS || currentBlock == Blocks.DARK_OAK_PLANKS ||
					currentBlock == Blocks.CHERRY_PLANKS || currentBlock == Blocks.MANGROVE_PLANKS ||
					currentBlock == Blocks.BAMBOO_PLANKS) {

				replacementBlock = ModBlocks.waste_planks;
			}

		} else if (currentBlock == Blocks.MOSSY_COBBLESTONE) {
			b = ModBlocks.ore_oil;
			chance = 50;

		} else if (currentBlock == Blocks.COAL_ORE) {
			b = Blocks.DIAMOND_ORE;
			chance = 10;

		} else if (currentBlock == ModBlocks.ore_uranium) {
			b = ModBlocks.ore_schrabidium;
			chance = 10;

		} else if (currentBlock == ModBlocks.ore_nether_uranium) {
			b = ModBlocks.ore_nether_schrabidium;
			chance = 10;

		} else if (currentBlock == ModBlocks.ore_nether_plutonium) {
			b = ModBlocks.ore_nether_schrabidium;
			chance = 25;

		} else if (currentBlock == Blocks.BROWN_MUSHROOM_BLOCK) {
			b = ModBlocks.waste_planks;

		} else if (currentBlock == Blocks.RED_MUSHROOM_BLOCK) {
			b = ModBlocks.waste_planks;

		} else if (currentBlock == Blocks.END_STONE) {
			b = ModBlocks.ore_tikite;
			chance = 1;

		} else if (currentBlock == Blocks.CLAY) {
			b = Blocks.TERRACOTTA;
		} else if (blockState.isFlammable(level, pos, null)) {
			replacementBlock = Blocks.FIRE;
			chance = 100;
		}

		if (replacementBlock != null && rand.nextInt(1000) < chance) {
			level.setBlock(pos, replacementBlock.defaultBlockState(), 2);
		}
	}
}