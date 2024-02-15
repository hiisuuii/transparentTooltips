package hisui.tt.mixin.client;

import hisui.tt.duck.GameOptionsAccess;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(TooltipBackgroundRenderer.class)
public abstract class TransparentToolTips {
	@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderRectangle(Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer$RectangleRenderer;Lorg/joml/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIII)V"), index = 8)
	private static int MoreTranslucentTooltipsMod$renderRectangleModifyArg(int color) {
		var client = net.minecraft.client.MinecraftClient.getInstance();
		var options = client.options;
		int value = (int) (((GameOptionsAccess)options).transparentTooltips$getToolTipOpacity().getValue() * 255.0);
		return (value << 24) & color;
	}
}