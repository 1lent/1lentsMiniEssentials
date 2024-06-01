package com.brodi.eesentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.brodi.eesentials.util.ConfigHandler;
import com.brodi.eesentials.util.PlayerMessage;

public class EatCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.eat"));
            player.setFoodLevel(20);
            player.setSaturation(20);

        }
        return true;
    }
}
