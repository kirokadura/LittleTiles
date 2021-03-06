package com.creativemd.littletiles.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class ItemBlockFlowingWater extends ItemBlock{

	public ItemBlockFlowingWater(Block block, ResourceLocation location) {
		super(block);
		setUnlocalizedName(location.getResourcePath());
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		String name = "default";
		if(stack.getItemDamage() < BlockLTFlowingWater.DIRECTION.getAllowedValues().size())
			name = EnumFacing.getFront(stack.getItemDamage()).getName();
		return getUnlocalizedName() + "." + name;
    }
	
	@Override
    public int getMetadata(int meta)
    {
		return meta;
    }

}
