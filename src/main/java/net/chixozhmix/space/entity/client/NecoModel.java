package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.NecoEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NecoModel extends GeoModel<NecoEntity> {
    @Override
    public ResourceLocation getModelResource(NecoEntity necoEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "geo/neco_arc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NecoEntity necoEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/neco_arc_tex.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NecoEntity necoEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "animations/neco_arc.animation.json");
    }

    @Override
    public void setCustomAnimations(NecoEntity animatable, long instanceId, AnimationState<NecoEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if( head != null) {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
