package com.brodi.eesentials.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import net.kyori.adventure.text.TextComponent;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {

    private static ConfigHandler instance;
    private static Plugin plugin;

    private FileConfiguration config;
    private File configFile;

    private ConfigHandler() {
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            createDefaultConfig();
        }
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static void init(Plugin pluginInstance) {
        if (instance == null) {
            plugin = pluginInstance;
            instance = new ConfigHandler();
        }
    }

    public static ConfigHandler getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ConfigHandler is not initialized. Call init(Plugin) first.");
        }
        return instance;
    }

    private void createDefaultConfig() {
        configFile.getParentFile().mkdirs();
        plugin.saveResource("config.yml", false);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public double getDouble(String path) {
        return config.getDouble(path);
    }

    public TextComponent getMessage(String path) {
        return PlayerMessage.formatColors(config.getString(path));
    }

    public void set(String path, Object value) {
        config.set(path, value);
        saveConfig();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    private void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
