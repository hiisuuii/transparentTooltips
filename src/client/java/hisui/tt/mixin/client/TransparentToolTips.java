package hisui.tt.mixin.client;

import hisui.tt.duck.GameOptionsAccess;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Screen.class)
public abstract class TransparentToolTips {
	@ModifyConstant(method = "renderTooltipFromComponents",
			constant = @Constant(intValue = -267386864), slice = @Slice(
					from = @At(value = "CONSTANT", args = "intValue=-267386864",ordinal = 5),
			to = @At(value = "CONSTANT", args = "intValue=-267386864",ordinal = 6)
	))
	private int MoreTranslucentTooltipsMod$modifyTooltipBackgroundColor(int color) {
		var client = net.minecraft.client.MinecraftClient.getInstance();
		var options = client.options;
		int value = (int) (((GameOptionsAccess)options).transparentTooltips$getToolTipOpacity().getValue() * 255.0);
		return (value << 24) & color;
	}
}