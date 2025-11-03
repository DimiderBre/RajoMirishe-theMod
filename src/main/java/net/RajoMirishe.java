package net;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RajoMirishe implements ModInitializer {
    public static final String MOD_ID = "rajomirishe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("RajoMirishe Initializing Block Break Event Listener!");

        PlayerBlockBreakEvents.AFTER.register((World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) -> {

            if (state.isOf(Blocks.DIRT) || state.isOf(Blocks.GRASS_BLOCK)) {

                if (!world.isClient()) {

                    Text message = Text.literal("Rajo mirishe!");

                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.getServer().getPlayerManager().broadcast(message, false);
                    }
                }
            }
        });
    }
}
