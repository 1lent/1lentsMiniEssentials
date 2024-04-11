package com.brodi.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            player.sendMessage("You are free of hunger!!");
            player.setFoodLevel(20);
            player.setSaturation(20);

        }






        return true;
    }
}
