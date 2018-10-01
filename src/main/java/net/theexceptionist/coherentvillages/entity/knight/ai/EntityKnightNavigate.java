package net.theexceptionist.coherentvillages.entity.knight.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityKnightNavigate extends PathNavigateGround{

	public EntityKnightNavigate(EntityLiving entitylivingIn, World worldIn) {
		super(entitylivingIn, worldIn);
	}
	
	public void onUpdateNavigation()
	{
		 ++this.totalTicks;

	        if (this.tryUpdatePath)
	        {
	            this.updatePath();
	        }

	        if (!this.noPath())
	        {
	            if (this.canNavigate())
	            {
	                this.pathFollow();
	            }
	            else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength())
	            {
	                Vec3d vec3d = this.getEntityPosition();
	                Vec3d vec3d1 = this.currentPath.getVectorFromIndex(this.entity, this.currentPath.getCurrentPathIndex());

	                if (vec3d.y > vec3d1.y && !this.entity.onGround && MathHelper.floor(vec3d.x) == MathHelper.floor(vec3d1.x) && MathHelper.floor(vec3d.z) == MathHelper.floor(vec3d1.z))
	                {
	                    this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
	                }
	            }

	            this.debugPathFinding();

	            if (!this.noPath())
	            {
	            	//System.out.println("Working");
	                Vec3d vec3d2 = this.currentPath.getPosition(this.entity);
	                BlockPos blockpos = (new BlockPos(vec3d2)).down();
	                AxisAlignedBB axisalignedbb = this.world.getBlockState(blockpos).getBoundingBox(this.world, blockpos);
	                vec3d2 = vec3d2.subtract(0.0D, 1.0D - axisalignedbb.maxY, 0.0D);
	                this.entity.getMoveHelper().setMoveTo(vec3d2.x, vec3d2.y, vec3d2.z, this.speed);
	            }
	        }
	}

}