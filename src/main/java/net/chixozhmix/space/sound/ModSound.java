package net.chixozhmix.space.sound;

import net.chixozhmix.space.ChiSpace;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ChiSpace.MOD_ID);


    public static final RegistryObject<SoundEvent> NECO_EMBIENT = registerSoundEvents("nya_nya");
    public static final RegistryObject<SoundEvent> NECO_HURT = registerSoundEvents("pyai_pyai");
    public static final RegistryObject<SoundEvent> NECO_EATING = registerSoundEvents("neco_eating");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ChiSpace.MOD_ID, name)));
    }


    public static void register(IEventBus eventBus)
    {
        SOUND_EVENT.register(eventBus);
    }
}
