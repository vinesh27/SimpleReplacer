package io.github.vinesh27.simplereplacer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class ReloadCommand implements CommandExecutor {
    private final SimpleReplacer plugin;
    public ReloadCommand(SimpleReplacer plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.reloadConfig();
        HashMap<String, String> map = new HashMap<>();
        plugin.getConfig().getConfigurationSection("replacements")
            .getKeys(false)
            .forEach(key ->
                map.put(key, plugin.getConfig().getString("replacements." + key)));
        SimpleReplacer.replacements = map;
        sender.sendMessage("Reloaded config");
        return true;
    }
}
