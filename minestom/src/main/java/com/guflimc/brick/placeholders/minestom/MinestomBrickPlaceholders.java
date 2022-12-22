package com.guflimc.brick.placeholders.minestom;

import com.guflimc.brick.placeholders.api.extension.AdvancedPlaceholderExtension;
import com.guflimc.brick.placeholders.minestom.api.MinestomPlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.extensions.Extension;

public class MinestomBrickPlaceholders extends Extension {

    @Override
    public void initialize() {
        getLogger().info("Enabling " + nameAndVersion() + ".");

        MinestomPlaceholderAPI.registerManager(new MinestomStandardPlaceholderManager());

        // default placeholders
        AdvancedPlaceholderExtension<Player> ext = MinestomPlaceholderAPI.get().registerExtension("player");
        ext.addReplacer("username", (p, d) -> Component.text(p.getUsername()));
        ext.addReplacer("displayname", (p, d) -> p.getName());

        getLogger().info("Enabled " + nameAndVersion() + ".");
    }

    @Override
    public void terminate() {
        getLogger().info("Disabled " + nameAndVersion() + ".");
    }

    private String nameAndVersion() {
        return getOrigin().getName() + " v" + getOrigin().getVersion();
    }

}
