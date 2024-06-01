package com.brodi.eesentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import com.brodi.eesentials.util.ConfigHandler;
import com.brodi.eesentials.util.PlayerMessage;

public class AnvilCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.anvil"));
            player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL, "Mobile Anvil"));
        }
        return true;
    }
}