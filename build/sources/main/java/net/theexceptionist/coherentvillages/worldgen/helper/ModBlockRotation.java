package net.theexceptionist.coherentvillages.worldgen.helper;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ModBlockRotation implements ITemplateProcessor
{
    private final float chance;
    private final Random random;

    public ModBlockRotation(BlockPos pos, ModPlacementSettings settings)
    {
        this.chance = settings.getIntegrity();
        this.random = settings.getRandom(pos);
    }

    @Nullable
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn)
    {
        return this.chance < 1.0F && this.random.nextFloat() > this.chance ? null : blockInfoIn;
    }
}