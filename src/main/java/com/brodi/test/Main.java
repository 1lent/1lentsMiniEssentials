package com.brodi.test;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("eat").setExecutor(new EatCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("anvil").setExecutor(new AnvilCommand());
        getCommand("echest").setExecutor(new EnderCommand());
        getCommand("grindstone").setExecutor(new GrindstoneCommand());

    }

}