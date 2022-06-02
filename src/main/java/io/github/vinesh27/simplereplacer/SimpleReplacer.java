package io.github.vinesh27.simplereplacer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class SimpleReplacer extends JavaPlugin {
    
    public static HashMap<String, Replacement> replacements = new HashMap<>();
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "SimpleReplacer enabled");
        
        this.saveDefaultConfig();
    
        if (this.getConfig().getConfigurationSection("replacements") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "No replacements found in config.yml");
        } else {
            getConfig().getConfigurationSection("replacements")
                .getKeys(false)
                .forEach(key ->
                    replacements.put(key,
                        new Replacement(
                            this.getConfig().getString("replacements." + key + ".replacement"),
                            this.getConfig().getBoolean("replacements." + key + ".hover"),
                            this.getConfig().getString("replacements." + key + ".hoverText")
                        )));
        }
        this.getCommand("replacerReload").setExecutor(new ReloadCommand(this));
        getServer().getPluginManager().registerEvents(new MessageEvent(), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Loaded " + replacements.size() + " replacements");
    }
    
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "SimpleReplacer disabled");
    }
}
