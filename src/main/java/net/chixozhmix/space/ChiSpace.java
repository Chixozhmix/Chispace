package net.chixozhmix.space;

import com.mojang.logging.LogUtils;
import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.block.entity.ModBlockEntities;
import net.chixozhmix.space.entity.ModEntities;
import net.chixozhmix.space.entity.client.FleshMimicRenderer;
import net.chixozhmix.space.entity.client.NecoRenderer;
import net.chixozhmix.space.entity.client.SummonedFleshBlobRenderer;
import net.chixozhmix.space.item.ModCreativeTab;
import net.chixozhmix.space.item.ModItems;
import net.chixozhmix.space.loot.ModLootModifiers;
import net.chixozhmix.space.screen.DesecrationAltarScreen;
import net.chixozhmix.space.screen.ModMenuTypes;
import net.chixozhmix.space.sound.ModSound;
import net.chixozhmix.space.worldgen.biome.ModTerrablender;
import net.chixozhmix.space.worldgen.biome.surface.ModSurfaceRules;
import net.chixozhmix.space.worldgen.feature.CustomGrassFeature;
import net.chixozhmix.space.worldgen.feature.CustomVinesFeature;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(net.chixozhmix.space.ChiSpace.MOD_ID)
public class ChiSpace
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "chispace";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLESH_VINES =
            FEATURES.register("flesh_vines", () ->
                    new CustomVinesFeature(15, ModBlocks.FLESH_VINE.get(), ModBlocks.FLESH_VINE_HEAD.get(), 10));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GUTS_VINES =
            FEATURES.register("guts_vines", () ->
                    new CustomVinesFeature(15, ModBlocks.GUTS.get(), ModBlocks.GUTS_HEAD.get(), 10));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> TENDONS_GRASS =
            FEATURES.register("tendons_grass", () ->
                    new CustomGrassFeature(2, ModBlocks.TENDONS.get(), 35));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BIO_LANTER =
            FEATURES.register("bio_lantern_gen", () ->
                    new CustomGrassFeature(2, com.github.elenterius.biomancy.init.ModBlocks.YELLOW_BIO_LANTERN.get(), 25));

    public ChiSpace(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        //CreativeTab and Menus
        ModCreativeTab.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        //LootModifiers
        ModLootModifiers.register(modEventBus);

        //Blocks and Items
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        //Entities
        ModEntities.register(modEventBus);

        //Sounds
        ModSound.register(modEventBus);

        //Biomes
        ModTerrablender.registerBiomes();

        //Features
        FEATURES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MOD_ID, ModSurfaceRules.makeRulesNether());
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.DESECRATION_ALTAR_MENU.get(), DesecrationAltarScreen::new);
            EntityRenderers.register(ModEntities.FLESH_MIMIC_ENTITY.get(), FleshMimicRenderer::new);
            EntityRenderers.register(ModEntities.SUMMONED_FLESH_BLOB.get(), SummonedFleshBlobRenderer::new);
            EntityRenderers.register(ModEntities.NECO_ENTITY.get(), NecoRenderer::new);
        }
    }
}
