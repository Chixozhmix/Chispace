package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.FleshMimicEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FleshMimicModel extends GeoModel<FleshMimicEntity> {
    @Override
    public ResourceLocation getModelResource(FleshMimicEntity fleshMimicEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "geo/flesh_mimic.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FleshMimicEntity fleshMimicEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/sdfasdfasdf.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FleshMimicEntity fleshMimicEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "animations/flesh_mimic_animation.json");
    }

    @Override
    public void setCustomAnimations(FleshMimicEntity animatable, long instanceId, AnimationState<FleshMimicEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if( head != null) {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
