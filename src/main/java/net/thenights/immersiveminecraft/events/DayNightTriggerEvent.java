package net.thenights.immersiveminecraft.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.thenights.immersiveminecraft.MainMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = MainMod.MOD_ID)
public class DayNightTriggerEvent {
    private static final Logger LOGGER = LogManager.getLogger();
    @SubscribeEvent
    public static void onPlayerEnterEvent(PlayerEvent.PlayerLoggedInEvent event) {
        LOGGER.info("Player Joined");
        int players = ServerLifecycleHooks.getCurrentServer().getPlayerCount();
        LOGGER.info("Player: "+ players);
        UpdateCycle(players);
    }
    @SubscribeEvent
    public static void onPlayerLeaveEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        LOGGER.info("Player left");
        int players = ServerLifecycleHooks.getCurrentServer().getPlayerCount()-1;
        LOGGER.info("Player: "+ players);
        UpdateCycle(players);
    }
    private static void UpdateCycle(int playerCount){
        boolean enabled = playerCount > 0;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        server.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(enabled, server);
        LOGGER.info("Day/Nigth Cycle: "+ ServerLifecycleHooks.getCurrentServer().getGameRules().getBoolean(GameRules.RULE_DAYLIGHT));
    }
}