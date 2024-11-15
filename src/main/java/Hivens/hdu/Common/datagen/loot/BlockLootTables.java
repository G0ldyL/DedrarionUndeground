package Hivens.hdu.Common.datagen.loot;

import Hivens.hdu.Common.Registry.BlockRegistry;
import Hivens.hdu.Common.Registry.ItemRegistry;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {

    public BlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockRegistry.ETHEREUM_BLOCK.get());
        this.dropSelf(BlockRegistry.RAW_ETHEREUM_BLOCK.get());
        this.dropSelf(BlockRegistry.SHARDS_OF_THE_STONE_OF_HOPES.get());

        this.add(BlockRegistry.ETHEREUM_ORE.get(),
                block -> createRareLikeOreDrops(BlockRegistry.ETHEREUM_ORE.get(), ItemRegistry.RAW_ETHEREUM.get()));
        this.add(BlockRegistry.STONE_OF_HOPES.get(),
                block -> createLikeStoneDrops(BlockRegistry.STONE_OF_HOPES.get(), BlockRegistry.SHARDS_OF_THE_STONE_OF_HOPES.get()));
    }


    protected LootTable.Builder createRareLikeOreDrops(@NotNull Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(ApplyBonusCount
                                        .addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createLikeStoneDrops(@NotNull Block pBlock, Block block) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(block)));
    }


    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
