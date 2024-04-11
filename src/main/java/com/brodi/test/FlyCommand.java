package com.brodi.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Toggle a player's ability to fly using the /fly command
 */
public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Run when the player runs the command:
        if (sender instanceof Player player) {
            if (player.getAllowFlight()) { // if player can fly - turn it off
                player.setFlying(false); // Make sure to turn off their current fly
                player.setAllowFlight(false); // Disable their ability to fly
                player.sendMessage("You can no longer fly");
            } else { // (else) if player could not fly - turn it on
                player.setAllowFlight(true); // turn fly on
                player.sendMessage("Flight has been enabled!");
            }
        }
        return true;
    }

}
// make most simple :D for dummies
