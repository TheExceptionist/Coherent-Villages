package net.theexceptionist.coherentvillages.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageHandlerSmallHouseWithDoor implements IVillageCreationHandler 
	{ 
	public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) 
	{ 
		//System.out.println("Registered");
	return new StructureVillagePieces.PieceWeight(VillageComponentSmallHouseWithDoor.class, 4, MathHelper.getInt(random, 2 + i, 4 + i * 2)); //Play around with these numbers! 
	} 

	public Class getComponentClass() 
	{ 
		return VillageComponentSmallHouseWithDoor.class; 
	} 

	@Override
	public Village buildComponent(PieceWeight villagePiece, Start startPiece,
			List<StructureComponent> pieces, Random random, int p1, int p2,
			int p3, EnumFacing facing, int p5) {
		// TODO Auto-generated method stub
		//System.out.println("Attempting to spawn village barracks");
		return VillageComponentSmallHouseWithDoor.createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
	} 
}

