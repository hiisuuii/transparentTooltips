package hisui.tt.mixin.client;

import hisui.tt.duck.GameOptionsAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TooltipBackgroundRenderer.class)
public abstract class TransparentToolTips {
	@ModifyArg(method = "render(Lnet/minecraft/client/gui/DrawContext;IIIII)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderRectangle(Lnet/minecraft/client/gui/DrawContext;IIIIII)V"), index = 6)
	private static int MoreTranslucentTooltipsMod$renderRectangleModifyArg(int color) {
		var client = net.minecraft.client.MinecraftClient.getInstance();
		var options = client.options;
		int value = (int) (((GameOptionsAccess)options).transparentTooltips$getToolTipOpacity().getValue() * 255.0);
		return (value << 24) & color;
	}
}