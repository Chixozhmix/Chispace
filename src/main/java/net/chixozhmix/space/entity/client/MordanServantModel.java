package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.MordanServantEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MordanServantModel extends GeoModel<MordanServantEntity> {
    @Override
    public ResourceLocation getModelResource(MordanServantEntity mordanServantEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "geo/servant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MordanServantEntity mordanServantEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/mordan_servant.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MordanServantEntity mordanServantEntity) {
        return new ResourceLocation(ChiSpace.MOD_ID, "animations/servant_animation.json");
    }

    @Override
    public void setCustomAnimations(MordanServantEntity animatable, long instanceId, AnimationState<MordanServantEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if( head != null) {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
