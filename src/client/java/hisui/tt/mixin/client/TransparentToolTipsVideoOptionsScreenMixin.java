package hisui.tt.mixin.client;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import hisui.tt.duck.GameOptionsAccess;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VideoOptionsScreen.class)
public abstract class TransparentToolTipsVideoOptionsScreenMixin {

    @ModifyReturnValue(method = "getOptions", at = @At("RETURN"))
    private static SimpleOption<?>[] addTooltipOpacitySetting(SimpleOption<?>[] original, GameOptions gameOptions){
        return ArrayUtils.add(original, ((GameOptionsAccess)gameOptions).transparentTooltips$getToolTipOpacity());
    }
}
