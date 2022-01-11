package com.gufli.brickplaceholders.api;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

public class PlaceholderAPI {

    private static PlaceholderManager placeholderManager;

    public static void registerManager(PlaceholderManager manager) {
        placeholderManager = manager;
    }

    public static Component replace(Player player, String text) {
        return placeholderManager.replace(player, text);
    }

    public static Collection<String> placeholders() {
        return placeholderManager.placeholders();
    }

    public static void registerReplacer(String key, BiFunction<Player, String, Component> replacer) {
        placeholderManager.registerReplacer(key, replacer);
    }

    public static void registerReplacer(String key, Function<Player, Component> replacer) {
        placeholderManager.registerReplacer(key, replacer);
    }

    public static void unregisterReplacer(String key) {
        placeholderManager.unregisterReplacer(key);
    }

}