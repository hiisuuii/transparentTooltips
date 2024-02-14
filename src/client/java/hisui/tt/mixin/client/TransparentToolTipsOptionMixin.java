package hisui.tt.mixin.client;

import hisui.tt.duck.GameOptionsAccess;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public abstract class TransparentToolTipsOptionMixin implements GameOptionsAccess {

    @Shadow
    private static Text getPercentValueText(Text p, double v){
        throw new AssertionError();
    }

    @Unique
    private static final Text TOOLTIP_OPACITY_TOOLTIP = Text.translatable("options.toolTipOpacity.tooltip");

    @Unique
    private final SimpleOption<Double> toolTipOpacity = new SimpleOption<>(
            "options.tooltip.opacity",
            SimpleOption.constantTooltip(TOOLTIP_OPACITY_TOOLTIP),
            (optionText, value) -> getPercentValueText(optionText, (Double)value),
            SimpleOption.DoubleSliderCallbacks.INSTANCE,
            (double)0xF0/0xFF,
            value -> {
            }
    );

    public SimpleOption<Double> transparentTooltips$getToolTipOpacity(){
        return this.toolTipOpacity;
    }

    @Inject(method = "accept", at = @At("TAIL"))
    private void inject(GameOptions.Visitor visitor, CallbackInfo ci){
        visitor.accept("toolTipOpacity", this.toolTipOpacity);
    }
}