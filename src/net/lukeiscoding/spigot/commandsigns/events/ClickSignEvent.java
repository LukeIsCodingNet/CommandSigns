package net.lukeiscoding.spigot.commandsigns.events;

import net.lukeiscoding.spigot.commandsigns.CommandSigns;
import net.lukeiscoding.spigot.commandsigns.util.SetupEconomy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

public class ClickSignEvent implements Listener {

    private static final CommandSigns plugin = CommandSigns.getPlugin(CommandSigns.class);
    private static final SetupEconomy economy = new SetupEconomy();

    @EventHandler(priority = EventPriority.HIGH)
    public void onSignChange(SignChangeEvent event) {
        if (event.getLine(0).equalsIgnoreCase("[CommandSign]")) {
            event.setLine(0, "§a[CommandSign]");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRightClick(PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        final Block block = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (block.getState() instanceof Sign) {
                if (((Sign) block.getState()).getLine(0) == "[CommandSign]") {
                    ((Sign) block.getState()).getLine(0).replaceAll("[CommandSign]", "§a[CommandSign]");
                }

                Command cmd = Bukkit.getServer().getPluginCommand(((Sign) block.getState()).getLine(1));
                if (cmd != null || ((Sign) block.getState()).getLine(1) != null) {
                    p.chat("/" + ((Sign) block.getState()).getLine(1));
                }

                if (plugin.getConfig().getBoolean("use-economy")) {
                    if (((Sign) block.getState()).getLine(2) != null) {
                        economy.getEconomy().withdrawPlayer(p, p.getWorld().getName(), Double.parseDouble(((Sign) block.getState()).getLine(2)));
                        if (economy.getEconomy().hasAccount(p)) {
                        } else if (!economy.getEconomy().hasAccount(p)) {
                            economy.getEconomy().createPlayerAccount(p);
                        }
                    }
                } else if (!plugin.getConfig().getBoolean("use-economy") && ((Sign) block.getState()).getLine(2) != null) {
                    ((Sign) block.getState()).getLine(0).replaceAll("§a[CommandSign]", "§c[CommandSign]");
                    p.sendMessage(ChatColor.RED + "Sign not valid! use-economy is set to false in the plugin config.yml file.");
                }
            }
        }
    }
}
