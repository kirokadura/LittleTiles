package com.creativemd.littletiles.client.tiles;

import com.creativemd.creativecore.client.rendering.RenderCubeObject;
import com.creativemd.creativecore.client.rendering.RenderHelper3D;
import com.creativemd.creativecore.common.utils.ColorUtils;
import com.creativemd.creativecore.common.utils.CubeObject;
import com.creativemd.littletiles.common.api.ILittleTile;
import com.creativemd.littletiles.common.tiles.vec.LittleTileBox;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LittleRenderingCube extends RenderCubeObject {
	
	public LittleTileBox box;
	
	public LittleRenderingCube(CubeObject cube, LittleTileBox box, Block block, int meta) {
		super(cube, block, meta);
		this.box = box;
	}
	
	public void renderCubePreview(boolean absolute, double x, double y, double z, ILittleTile iTile)
	{
		Vec3d size = getSize();
		double cubeX = x+minX+size.xCoord/2D;
		if(absolute)
			cubeX -= x+TileEntityRendererDispatcher.staticPlayerX;
		
		double cubeY = y+minY+size.yCoord/2D;
		if(absolute)
			cubeY -= y+TileEntityRendererDispatcher.staticPlayerY;
		
		double cubeZ = z+minZ+size.zCoord/2D;
		if(absolute)
			cubeZ -= z+TileEntityRendererDispatcher.staticPlayerZ;
		
		Vec3d color = ColorUtils.IntToVec(this.color);
		RenderHelper3D.renderBlock(cubeX, cubeY, cubeZ, size.xCoord, size.yCoord, size.zCoord, 0, 0, 0, color.xCoord, color.yCoord, color.zCoord, (Math.sin(System.nanoTime()/200000000D)*0.2+0.5) * iTile.getPreviewAlphaFactor());
	}
}