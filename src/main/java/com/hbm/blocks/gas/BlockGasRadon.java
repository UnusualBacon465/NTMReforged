package com.hbm.blocks.gas;

import java.util.Random;

import com.hbm.extprop.HbmLivingProps;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorUtil;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;

public class BlockGasRadon extends BlockGasBase {

	public BlockGasRadon() {
		super(0.1F, 0.8F, 0.1F);
	}

	@Override
	public void onEntityCollidedWithBlock(Level level, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity) {
		
		if(!(entity instanceof EntityLivingBase))
			return;
		
		EntityLivingBase entityLiving = (EntityLivingBase) entity;
		
		if(ArmorRegistry.hasAllProtection(entityLiving, 3, HazardClass.PARTICLE_FINE)) {
			ArmorUtil.damageGasMaskFilter(entityLiving, 1);
		} else {
			ContaminationUtil.contaminate((EntityLivingBase)entity, HazardType.RADIATION, ContaminationType.RAD_BYPASS, 0.05F);
			HbmLivingProps.incrementAsbestos((EntityLivingBase)entity, 1); 
		}
	}

	@Override
	public Direction getFirstDirection(Level level, int x, int y, int z) {
		
		if(level.random.nextInt(5) == 0)
			return Direction.UP;
		
		return Direction.DOWN;
	}

	@Override
	public Direction getSecondDirection(Level level, int x, int y, int z) {
		return this.randomHorizontal(world);
	}

	@Override
	public void updateTick(Level level, int x, int y, int z, Random rand) {

		if(!level.isClientSide && rand.nextInt(50) == 0) {
			level.setBlockToAir(x, y, z);
			return;
		}
		
		super.updateTick(level, x, y, z, rand);
	}
}
