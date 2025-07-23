package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.SummonedFleshBlob;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SummonedFleshBlobRenderer extends GeoEntityRenderer<SummonedFleshBlob> {
    public SummonedFleshBlobRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SummonedFleshBlobModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SummonedFleshBlob animatable) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/summoned_flesh_blob.png");
    }
}
