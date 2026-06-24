package net.trique.mythicupgrades;

public class CommonClass {

    public static void init() {
        MythicLegacyMigration.init();
        Constants.LOG.info("MythicUpgrades initialized on {}!", Constants.MOD_ID);
    }
}
