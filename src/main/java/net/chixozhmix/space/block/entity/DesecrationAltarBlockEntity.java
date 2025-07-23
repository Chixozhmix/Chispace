package net.chixozhmix.space.block.entity;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.chixozhmix.space.item.ModItems;
import net.chixozhmix.space.screen.DesecrationAltarMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DesecrationAltarBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(6);

    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT1 = 1;
    private static final int INPUT_SLOT2 = 2;
    private static final int INPUT_SLOT3 = 3;
    private static final int INPUT_SLOT4 = 4;
    private static final int OUTPUT_SLOT = 5;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public DesecrationAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.DESECRATION_ALTAR.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> DesecrationAltarBlockEntity.this.progress;
                    case 1 -> DesecrationAltarBlockEntity.this.progress;
                    case 2 -> DesecrationAltarBlockEntity.this.progress;
                    case 3 -> DesecrationAltarBlockEntity.this.progress;
                    case 4 -> DesecrationAltarBlockEntity.this.progress;
                    case 5 -> DesecrationAltarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> DesecrationAltarBlockEntity.this.progress = pValue;
                    case 1 -> DesecrationAltarBlockEntity.this.progress = pValue;
                    case 2 -> DesecrationAltarBlockEntity.this.progress = pValue;
                    case 3 -> DesecrationAltarBlockEntity.this.progress = pValue;
                    case 4 -> DesecrationAltarBlockEntity.this.progress = pValue;
                    case 5 -> DesecrationAltarBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.chispace.desecration_altar");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pConteinerId, Inventory inventory, Player player) {
        return new DesecrationAltarMenu(pConteinerId, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("desecration_altar.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("desecration_altar.progress");
    }

    public void tick(Level pLevel, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, blockPos, blockState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.BLACK_SOUL_GEM.get(), 1);
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT2, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT3, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT4, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ItemRegistry.BLOOD_VIAL.get();
        boolean hasCraftingItem1 = this.itemHandler.getStackInSlot(INPUT_SLOT1).getItem() == ItemRegistry.BLOOD_VIAL.get();
        boolean hasCraftingItem2 = this.itemHandler.getStackInSlot(INPUT_SLOT2).getItem() == ItemRegistry.BLOOD_VIAL.get();
        boolean hasCraftingItem3 = this.itemHandler.getStackInSlot(INPUT_SLOT3).getItem() == ItemRegistry.BLOOD_VIAL.get();
        boolean hasCraftingItem4 = this.itemHandler.getStackInSlot(INPUT_SLOT4).getItem() == ModItems.SOUL_GEM.get();

        ItemStack result = new ItemStack(ModItems.BLACK_SOUL_GEM.get());

        return hasCraftingItem && hasCraftingItem1 && hasCraftingItem2 && hasCraftingItem3 && hasCraftingItem4 &&
                canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <=
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }


}
