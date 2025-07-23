package net.chixozhmix.space.entity.client;

import net.chixozhmix.space.ChiSpace;
import net.chixozhmix.space.entity.custom.SummonedFleshBlob;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SummonedFleshBlobModel extends GeoModel<SummonedFleshBlob> {
    @Override
    public ResourceLocation getModelResource(SummonedFleshBlob summonedFleshBlob) {
        return new ResourceLocation(ChiSpace.MOD_ID, "geo/summoned_flesh_blob.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SummonedFleshBlob summonedFleshBlob) {
        return new ResourceLocation(ChiSpace.MOD_ID, "textures/entity/summoned_flesh_blob.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SummonedFleshBlob summonedFleshBlob) {
        return new ResourceLocation(ChiSpace.MOD_ID, "animations/summoned_flesh_blob_animation.json");
    }

    @Override
    public void setCustomAnimations(SummonedFleshBlob animatable, long instanceId, AnimationState<SummonedFleshBlob> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if( head != null) {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
