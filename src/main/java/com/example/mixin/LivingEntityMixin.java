package com.example.mixin;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Mob.class)
public abstract class LivingEntityMixin {
    @Inject(method = "canAttack", at = @At("HEAD"), cancellable = true)
    private void bedmonster$doNotConsiderSleepingPlayersTargets(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if (target instanceof Player player && player.isSleeping()) cir.setReturnValue(false);
    }
}