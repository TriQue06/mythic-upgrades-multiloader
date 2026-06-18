package net.trique.mythicupgrades.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.block.entity.MythicUpgradingTableBlockEntity;

/**
 * Holds references to registered block entity types.
 * The actual BlockEntityType instances are created and registered
 * by each platform (Forge/Fabric) in their respective registration code,
 * because BlockEntityType.Builder uses a package-private supplier interface.
 */
public class MythicBlockEntityTypes {

    public static BlockEntityType<MythicUpgradingTableBlockEntity> UPGRADING_TABLE;

    /**
     * Called by platform code once UPGRADING_TABLE has been set.
     */
    public static void onRegistered() {
        Constants.LOG.info("MythicBlockEntityTypes registered.");
    }
}
