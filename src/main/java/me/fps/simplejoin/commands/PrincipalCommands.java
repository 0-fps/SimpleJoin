package me.fps.simplejoin.commands;

import me.fps.simplejoin.SimpleJoin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrincipalCommands implements CommandExecutor {
    private SimpleJoin plugin;

    public PrincipalCommands(SimpleJoin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command commands, String label, String[] args) {
        Player jugador = (Player) sender;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("version")) {
                if (jugador.hasPermission("simplejoin.admin")) {
                    jugador.sendMessage(plugin.name + ChatColor.GREEN + " the version of plugin is: " + plugin.version);
                    return true;
                } else {
                    jugador.sendMessage(ChatColor.RED + "You don't have permission to do this");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (jugador.hasPermission("simplejoin.admin")) {
                    plugin.reloadConfig();
                    jugador.sendMessage(plugin.name + ChatColor.GREEN + " the plugin has been reloaded");
                    return true;
                } else {
                    jugador.sendMessage(ChatColor.RED + "You don't have permission to do this");
                    return true;

                }
            }
        } else {
            jugador.sendMessage(plugin.name + ChatColor.RED + " this command doesn't exist");
        }
        return true;
    }

}
