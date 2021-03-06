package com.creativemd.littletiles.common.packet;

import com.creativemd.creativecore.common.packet.CreativeCorePacket;
import com.creativemd.littletiles.common.api.ILittleTile;
import com.creativemd.littletiles.common.api.ISpecialBlockSelector;
import com.creativemd.littletiles.common.structure.LittleStructure;
import com.creativemd.littletiles.common.utils.grid.LittleGridContext;
import com.creativemd.littletiles.common.utils.placing.PlacementHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;

public class LittleFlipPacket extends CreativeCorePacket{
	
	public LittleFlipPacket() {
		
	}
	
	public Axis axis;
	
	public LittleFlipPacket(Axis axis) {
		this.axis = axis;
	}
	
	@Override
	public void writeBytes(ByteBuf buf) {
		buf.writeInt(axis.ordinal());
	}

	@Override
	public void readBytes(ByteBuf buf) {
		axis = Axis.values()[buf.readInt()];
	}

	@Override
	public void executeClient(EntityPlayer player) {
		execute(player);
	}
	
	public void execute(EntityPlayer player)
	{
		ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
		if(PlacementHelper.getLittleInterface(stack) != null)
		{
			ILittleTile itile = PlacementHelper.getLittleInterface(stack);
			
			if(itile != null)
			{
				LittleGridContext context = itile.flipLittlePreview(stack, axis);
				LittleStructure structure = itile.getLittleStructure(stack);
				if(structure != null)
				{
					structure.onFlip(player.world, player, stack, axis, context.rotationCenter);
					NBTTagCompound nbt = new NBTTagCompound();
					structure.writeToNBT(nbt);
					stack.getTagCompound().setTag("structure", nbt);
				}
			}
		}
		
		if(stack.getItem() instanceof ISpecialBlockSelector)
		{
			((ISpecialBlockSelector) stack.getItem()).flipLittlePreview(stack, axis);
		}
	}

	@Override
	public void executeServer(EntityPlayer player) {
		execute(player);
	}

}
