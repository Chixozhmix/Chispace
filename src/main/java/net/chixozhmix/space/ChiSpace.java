package net.chixozhmix.space;

import com.mojang.logging.LogUtils;
import net.chixozhmix.space.block.ModBlocks;
import net.chixozhmix.space.block.entity.ModBlockEntities;
import net.chixozhmix.space.entity.ModEntities;
import net.chixozhmix.space.entity.client.FleshMimicRenderer;
import net.chixozhmix.space.entity.client.SummonedFleshBlobRenderer;
import net.chixozhmix.space.item.ModCreativeTab;
import net.chixozhmix.space.item.ModItems;
import net.chixozhmix.space.loot.ModLootModifiers;
import net.chixozhmix.space.screen.DesecrationAltarScreen;
import net.chixozhmix.space.screen.ModMenuTypes;
import net.chixozhmix.space.worldgen.biome.ModTerrablender;
import net.chixozhmix.space.worldgen.biome.surface.ModSurfaceRules;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
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

        //Biomes
        ModTerrablender.registerBiomes();

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
        }
    }
}
