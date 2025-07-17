package com.hbm.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class BlockRegistrar {

    private final DeferredRegister<Block> blocks;
    private final DeferredRegister<Item> items;

    public BlockRegistrar(String modid) {
        this.blocks = DeferredRegister.create(BuiltInRegistries.BLOCK, modid);
        this.items = DeferredRegister.create(BuiltInRegistries.ITEM, modid);
    }

    public DeferredRegister<Block> getBlockRegister() { return blocks; }
    public DeferredRegister<Item> getItemRegister() { return items; }

    public <T extends Block> Supplier<T> register(String name, Supplier<T> blockSupplier, CreativeModeTabs tab) {
        Supplier<T> block = blocks.register(name, blockSupplier);
        items.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    // Optional: register a fluid block only (bucket/item separate)
    public Supplier<LiquidBlock> registerFluidBlock(String name, Supplier<FlowingFluid> fluidSupplier) {
        return blocks.register(name, () -> new LiquidBlock(fluidSupplier.get(), BlockBehaviour.Properties.of().noCollission().strength(100.0F)));
    }

    public <T extends Block> Supplier<Block> register(String exampleBlock, Supplier<T> tSupplier, ResourceKey<CreativeModeTab> buildingBlocks) {
        return null;
    }
}