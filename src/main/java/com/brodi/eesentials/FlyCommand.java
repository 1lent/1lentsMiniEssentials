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

public class FlyCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Player target = player;

            if (args.length > 0) {
                target = Bukkit.getPlayer(args[0]);
                if (!player.hasPermission("essentials.admin")) {
                    PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.nopermission"));
                    return false;
                }
                if (target == null) {
                    PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.player.notfound").replace("%player%", args[0]));
                    return true;
                }
            }

            if (target.getAllowFlight()) {
                target.setFlying(false);
                target.setAllowFlight(false);
                PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.flight.disabled").replace("%player%", target.getName()));
                if (!target.equals(player)) {
                    PlayerMessage.send(target, ConfigHandler.getInstance().getString("messages.flight.disabled"));
                }
            } else {
                target.setAllowFlight(true);
                PlayerMessage.send(player, ConfigHandler.getInstance().getString("messages.flight.enabled").replace("%player%", target.getName()));
                if (!target.equals(player)) {
                    PlayerMessage.send(target, ConfigHandler.getInstance().getString("messages.flight.enabled"));
                }
            }
        }
        return true;
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
