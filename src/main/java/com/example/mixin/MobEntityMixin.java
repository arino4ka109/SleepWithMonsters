package com.example.mixin;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Mob.class)
public abstract class MobEntityMixin {
    @Shadow private LivingEntity target;
    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void bedmonster$doNotTargetSleepingPlayers(LivingEntity target, CallbackInfo ci) {
        if (target instanceof Player player && player.isSleeping()) { this.target = null; ci.cancel(); }
    }
    @Inject(method = "doHurtTarget", at = @At("HEAD"), cancellable = true)
    private void bedmonster$doNotHitSleepingPlayers(ServerLevel level, Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (target instanceof Player player && player.isSleeping()) { this.target = null; cir.setReturnValue(false); }
    }
}