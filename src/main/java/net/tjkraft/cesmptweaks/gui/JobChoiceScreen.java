package net.tjkraft.cesmptweaks.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tjkraft.cesmptweaks.CreateEconomySMPTweaks;
import net.tjkraft.cesmptweaks.network.CESMPTweaksNetwork;
import net.tjkraft.cesmptweaks.network.custom.GiveRolePacket;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class JobChoiceScreen extends Screen {
    private final static ResourceLocation GUI = new ResourceLocation(CreateEconomySMPTweaks.MOD_ID, "textures/gui/choose_your_job_gui.png");
    private final int imageWidth = 140;
    private final int imageHeight = 80;
    private int leftPos, topPos;

    public JobChoiceScreen(Component pTitle) {
        super(Component.literal("Choose your job").withStyle(ChatFormatting.WHITE));
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - imageWidth) / 2;
        this.topPos = (this.height - imageHeight) / 2;

        addButton(0, leftPos + 18, topPos + 18, "Engineer");
        addButton(1, leftPos + 48, topPos + 18, "Warrior");
        addButton(2, leftPos + 78, topPos + 18, "Wizard");
        addButton(3, leftPos + 108, topPos + 18, "Carpenter");
        addButton(4, leftPos + 18, topPos + 48, "Farmer");
        addButton(5, leftPos + 48, topPos + 48, "Cook");
        addButton(6, leftPos + 78, topPos + 48, "Blacksmith");
        addButton(7, leftPos + 108, topPos + 48, "Collector");
    }

    private void addButton(int id, int x, int y, String label) {
        ImageButton button = new ImageButton(x, y, 16, 16, 0, 0, 0, new ResourceLocation("minecraft", "textures/item/diamond.png"), pButton -> {
            CESMPTweaksNetwork.INSTANCE.sendToServer(new GiveRolePacket(id));
            this.onClose();
        });
        button.setTooltip(Tooltip.create(Component.translatable("jobChoice.cesmptweaks." + label.toLowerCase(Locale.ROOT))));
        this.addRenderableWidget(button);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        pGuiGraphics.blit(GUI, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}
