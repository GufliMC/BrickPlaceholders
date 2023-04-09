package com.guflimc.brick.placeholders.spigot;

import com.guflimc.brick.placeholders.api.module.BasePlaceholderModule;
import com.guflimc.brick.placeholders.api.resolver.PlaceholderResolver;
import com.guflimc.brick.placeholders.spigot.api.SpigotPlaceholderAPI;
import com.guflimc.brick.placeholders.spigot.placeholderapi.PlaceholderAPIModule;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotBrickPlaceholders extends JavaPlugin {

    private SpigotBrickPlaceholderManager manager;

    @Override
    public void onEnable() {
        getLogger().info("Enabling " + nameAndVersion() + ".");

        manager = new SpigotBrickPlaceholderManager(this);
        SpigotPlaceholderAPI.registerManager(manager);

        // delegate to other placeholder plugins
        placeholderapi();

        // built-in placeholder modules
        player();

        getLogger().info("Enabled " + nameAndVersion() + ".");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled " + nameAndVersion() + ".");
    }

    private String nameAndVersion() {
        return getName() + " v" + getDescription().getVersion();
    }

    //

    public boolean isPlaceholderAPI() {
        return getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    //

    public void placeholderapi() {
        if ( !isPlaceholderAPI() ) {
            return;
        }

        getLogger().info("Hooking into PlaceholderAPI...");

        // use placeholderAPI placeholders through BrickPlaceholders
        manager.addDelegate(new PlaceholderAPIModule());
    }

    public void player() {
        BasePlaceholderModule<Player> module = new BasePlaceholderModule<>("player");
        module.register("name", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().getName()));
        module.register("displayname", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().getDisplayName()));
        module.register("health", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().getHealth()));
        module.register("level", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().getLevel()));
        module.register("exp", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().getExp()));
        module.register("food", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().getFoodLevel()));
        module.register("is_flying", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().isFlying()));
        module.register("is_sneaking", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().isSneaking()));
        module.register("is_sprinting", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().isSprinting()));
        module.register("is_op", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().isOp()));
        module.register("is_invisible", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().isInvisible()));
        module.register("is_invulnerable", PlaceholderResolver.withEntity((p, ctx) -> ctx.entity().isInvulnerable()));
        manager.register(module);
    }


}
