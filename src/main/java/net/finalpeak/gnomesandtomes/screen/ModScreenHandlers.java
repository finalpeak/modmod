package net.finalpeak.gnomesandtomes.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ImbuingScreenHandler> IMBUING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(GnomesAndTomes.MOD_ID, "imbuing"),
                    new ExtendedScreenHandlerType<>(ImbuingScreenHandler::new));

    public static void registerScreenHandlers() {
        GnomesAndTomes.LOGGER.info("Registering Screen Handlers for " + GnomesAndTomes.MOD_ID);
    }
}
