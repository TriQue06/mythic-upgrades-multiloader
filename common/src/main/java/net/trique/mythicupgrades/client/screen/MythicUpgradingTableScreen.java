package net.trique.mythicupgrades.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.trique.mythicupgrades.menu.MythicUpgradingTableMenu;
import org.joml.Quaternionf;

public class MythicUpgradingTableScreen extends AbstractContainerScreen<MythicUpgradingTableMenu>
        implements ContainerListener {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("mythicupgrades", "textures/gui/mythic_upgrading_table.png");

    private static final Quaternionf ARMOR_STAND_ANGLE =
            new Quaternionf().rotationXYZ(0.43633232F, 0.0F, (float) Math.PI);
    private static final int ARMOR_STAND_SCALE = 22;
    private static final int ARMOR_STAND_OFFSET_X = 153;
    private static final int ARMOR_STAND_OFFSET_Y = 60;

    private static final int FIRE_TEX_X = 176;
    private static final int FIRE_TEX_Y = 0;
    private static final int FIRE_W = 14;
    private static final int FIRE_H = 13;
    private static final int FIRE_GUI_X = 19;
    private static final int FIRE_GUI_Y = 36;

    private static final int BAR_TEX_X = 176;
    private static final int BAR_TEX_Y = 13;
    private static final int BAR_MAX_W = 60;
    private static final int BAR_H = 5;
    private static final int BAR_GUI_X = 58;
    private static final int BAR_GUI_Y = 79;

    private static final int ARROW_EMPTY_TEX_Y = 18;
    private static final int ARROW_FILLED_TEX_Y = 33;
    private static final int ARROW_TEX_X = 176;
    private static final int ARROW_W = 22;
    private static final int ARROW_H = 15;
    private static final int ARROW_GUI_X = 90;
    private static final int ARROW_GUI_Y = 47;

    private ArmorStand armorStand;

    public MythicUpgradingTableScreen(MythicUpgradingTableMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null) {
            armorStand = new ArmorStand(mc.level, 0, 0, 0);
            armorStand.setNoBasePlate(true);
            armorStand.setShowArms(true);
            armorStand.yBodyRot = 210.0F;
            armorStand.setXRot(25.0F);
            armorStand.yHeadRot = armorStand.getYRot();
            armorStand.yHeadRotO = armorStand.getYRot();
        }
        updateArmorStand(menu.getSlot(5).getItem());
        menu.addSlotListener(this);
    }

    @Override
    public void removed() {
        super.removed();
        menu.removeSlotListener(this);
    }

    @Override
    public void slotChanged(AbstractContainerMenu container, int slotIndex, ItemStack stack) {
        updateArmorStand(menu.getSlot(5).getItem());
    }

    @Override
    public void dataChanged(AbstractContainerMenu container, int dataSlotIndex, int value) {}

    private void updateArmorStand(ItemStack stack) {
        if (armorStand == null) return;
        for (EquipmentSlot eq : EquipmentSlot.values()) {
            armorStand.setItemSlot(eq, ItemStack.EMPTY);
        }
        if (!stack.isEmpty()) {
            if (stack.getItem() instanceof ArmorItem armor) {
                armorStand.setItemSlot(armor.getEquipmentSlot(), stack.copy());
            } else {
                armorStand.setItemSlot(EquipmentSlot.OFFHAND, stack.copy());
            }
        }
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;

        g.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (menu.isBurning()) {
            int fireH = menu.getBurnProgress();
            if (fireH > 0) {
                g.blit(TEXTURE,
                        x + FIRE_GUI_X, y + FIRE_GUI_Y + (FIRE_H - fireH),
                        FIRE_TEX_X, FIRE_TEX_Y + (FIRE_H - fireH),
                        FIRE_W, fireH);
            }
        }

        int barW = menu.getNecoiumBarWidth();
        if (barW > 0) {
            g.blit(TEXTURE, x + BAR_GUI_X, y + BAR_GUI_Y, BAR_TEX_X, BAR_TEX_Y, barW, BAR_H);
        }

        boolean ready = !menu.getSlot(5).getItem().isEmpty();
        int arrowTexY = ready ? ARROW_FILLED_TEX_Y : ARROW_EMPTY_TEX_Y;
        g.blit(TEXTURE, x + ARROW_GUI_X, y + ARROW_GUI_Y, ARROW_TEX_X, arrowTexY, ARROW_W, ARROW_H);

        if (armorStand != null) {
            InventoryScreen.renderEntityInInventory(
                    g,
                    x + ARMOR_STAND_OFFSET_X,
                    y + ARMOR_STAND_OFFSET_Y,
                    ARMOR_STAND_SCALE,
                    ARMOR_STAND_ANGLE,
                    null,
                    armorStand
            );
        }
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
        g.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x404040, false);
        g.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x404040, false);
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g);
        updateArmorStand(menu.getSlot(5).getItem());
        super.render(g, mouseX, mouseY, partialTick);
        this.renderTooltip(g, mouseX, mouseY);
    }
}
