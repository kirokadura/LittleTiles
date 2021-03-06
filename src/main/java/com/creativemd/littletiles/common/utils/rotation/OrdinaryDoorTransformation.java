package com.creativemd.littletiles.common.utils.rotation;

import javax.vecmath.Matrix3f;

import com.creativemd.creativecore.common.utils.Rotation;
import com.creativemd.creativecore.common.utils.RotationUtils;
import com.creativemd.littletiles.common.entity.EntityAnimation;
import com.creativemd.littletiles.common.entity.EntityDoorAnimation;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;

public class OrdinaryDoorTransformation extends DoorTransformation{
	
	public Rotation rotation;
	
	public OrdinaryDoorTransformation() {
		
	}
	
	public OrdinaryDoorTransformation(Rotation rotation) {
		this.rotation = rotation;
	}

	@Override
	public void performTransformation(EntityDoorAnimation animation, double progress) {
		switch(rotation)
		{
		case X_CLOCKWISE:
			animation.worldRotX = -90+progress*90;
			break;
		case X_COUNTER_CLOCKWISE:
			animation.worldRotX = (1-progress)*90;
			break;
		case Y_CLOCKWISE:
			animation.worldRotY = -90+progress*90;
			break;
		case Y_COUNTER_CLOCKWISE:
			animation.worldRotY = (1-progress)*90;
			break;
		case Z_CLOCKWISE:
			animation.worldRotZ = -90+progress*90;
			break;
		case Z_COUNTER_CLOCKWISE:
			animation.worldRotZ = (1-progress)*90;
			break;	
		}
	}

	@Override
	protected void writeToNBTExtra(NBTTagCompound nbt) {
		nbt.setInteger("rot", rotation.ordinal());
	}

	@Override
	protected void readFromNBT(NBTTagCompound nbt) {
		rotation = Rotation.values()[nbt.getInteger("rot")];
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof OrdinaryDoorTransformation)
			return ((OrdinaryDoorTransformation) object).rotation == rotation;
		return false;
	}

}
