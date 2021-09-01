package net.lukeiscoding.spigot.commandsigns;

import net.lukeiscoding.spigot.commandsigns.commands.CommandCommandSigns;
import net.lukeiscoding.spigot.commandsigns.events.ClickSignEvent;
import org.bukkit.plugin.java.JavaPlugin;

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

public class CommandSigns extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        registerEvents();
    }

    private void registerCommands() {
        this.getCommand("commandsigns").setExecutor(new CommandCommandSigns());
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new ClickSignEvent(), this);
    }
}
