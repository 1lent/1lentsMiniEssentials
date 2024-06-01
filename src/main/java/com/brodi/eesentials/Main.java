package com.brodi.eesentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.brodi.eesentials.util.ConfigHandler;
import com.brodi.eesentials.util.PlayerMessage;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("eat").setExecutor(new EatCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("anvil").setExecutor(new AnvilCommand());
        getCommand("echest").setExecutor(new EnderCommand());
        getCommand("grindstone").setExecutor(new GrindstoneCommand());

        ConfigHandler.init(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            ConfigHandler.getInstance().reloadConfig();
            PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.reloaded"));
        }
        return true;
    }


}