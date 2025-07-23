package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.FleshMimicEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FleshMimicRenderer extends GeoEntityRenderer<FleshMimicEntity> {
    public FleshMimicRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FleshMimicModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FleshMimicEntity animatable) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/sdfasdfasdf.png");
    }
}
