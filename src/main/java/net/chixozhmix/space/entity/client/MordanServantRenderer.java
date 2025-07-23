package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.MordanServantEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MordanServantRenderer extends GeoEntityRenderer<MordanServantEntity> {
    public MordanServantRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MordanServantModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MordanServantEntity animatable) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/mordan_servant.png");
    }


}
