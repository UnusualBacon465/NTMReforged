package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.oil.TileEntityMachineLiquefactor;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderLiquefactor extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		TileEntityMachineLiquefactor liq = (TileEntityMachineLiquefactor) tileEntity;
		
		GL11.glShadeModel(GL11.GL_SMOOTH);
		bindTexture(ResourceManager.liquefactor_tex);
		ResourceManager.liquefactor.renderPart("Main");
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		if(liq.tank.getFill() > 0) {
			int color = liq.tank.getTankType().getColor();
			GL11.glColor3ub((byte) ((color & 0xFF0000) >> 16), (byte) ((color & 0x00FF00) >> 8), (byte) ((color & 0x0000FF)));
			
			double height = (double)liq.tank.getFill() / (double)liq.tank.getMaxFill();
			GL11.glPushMatrix();
			GL11.glTranslated(0, 1, 0);
			GL11.glScaled(1, height, 1);
			GL11.glTranslated(0, -1, 0);
			ResourceManager.liquefactor.renderPart("Fluid");
			GL11.glPopMatrix();
		}
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(0.75F, 1.0F, 1.0F, 0.15F);
		GL11.glDepthMask(false);
		
		ResourceManager.liquefactor.renderPart("Glass");

		GL11.glDepthMask(true);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
