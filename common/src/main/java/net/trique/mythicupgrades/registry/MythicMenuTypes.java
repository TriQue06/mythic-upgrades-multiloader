package net.trique.mythicupgrades.registry;

import net.minecraft.world.inventory.MenuType;
import net.trique.mythicupgrades.menu.MythicUpgradingTableMenu;

/**
 * Holds references to registered menu types.
 * The actual MenuType instances are created and registered
 * by each platform (Forge/Fabric) in their respective registration code,
 * because MenuType construction requires platform-specific APIs.
 */
public class MythicMenuTypes {

    public static MenuType<MythicUpgradingTableMenu> UPGRADING_TABLE;
}
