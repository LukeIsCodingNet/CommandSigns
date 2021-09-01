package net.lukeiscoding.spigot.commandsigns.commands;

import net.lukeiscoding.spigot.commandsigns.CommandSigns;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/*
    CommandSigns is a plugin that allows signs to execute commands, while also allowing the option to charge players money to use the signs.
    Copyright (C) 2021 Luke Is Coding

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

public class CommandCommandSigns implements CommandExecutor {

    private static final CommandSigns plugin = CommandSigns.getPlugin(CommandSigns.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (sender instanceof Player && sender instanceof ConsoleCommandSender) {
            if (sender.hasPermission("commandsigns.admin") && p.hasPermission("commandsigns.admin")) {
                if (cmd.getName().contains("commandsigns")) {
                    if (args.length == 0) {
                        sender.sendMessage(ChatColor.RED + "Error in command syntax, please see: /commandsigns help.");
                    }

                    if (args[1].equalsIgnoreCase("help")) {
                        sender.sendMessage(ChatColor.AQUA + "--- CommandSigns Help ---");
                        sender.sendMessage(ChatColor.AQUA + "/commandsigns help shows this help message.");
                        sender.sendMessage(ChatColor.AQUA + "/commandsigns reload reloads the config file for this plugin.");
                    }

                    if (args[1].equalsIgnoreCase("reload")) {
                        plugin.reloadConfig();
                    }
                }
            }
        }

        return false;
    }
}
