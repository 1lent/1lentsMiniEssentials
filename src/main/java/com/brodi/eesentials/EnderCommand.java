package com.brodi.eesentials;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.brodi.eesentials.util.ConfigHandler;
import com.brodi.eesentials.util.PlayerMessage;

public class EnderCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length < 1) {
                PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.enderchest.self"));
                player.openInventory(player.getEnderChest());
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null && player.hasPermission("essentials.admin")) {
                    PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.enderchest.other").replace("%player%", target.getName()));
                    player.openInventory(target.getEnderChest());
                } else {
                    if (!player.hasPermission("essentials.admin")) {
                        PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.nopermission"));
                        return false;
                    }
                    PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.player.notfound").replace("%player%", args[0]));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String partialName = args[0];
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().contains(partialName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
