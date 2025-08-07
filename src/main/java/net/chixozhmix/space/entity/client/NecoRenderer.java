package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.NecoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NecoRenderer extends GeoEntityRenderer<NecoEntity> {
    public NecoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NecoModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NecoEntity animatable) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/neco_arc_tex.png");
    }
}
