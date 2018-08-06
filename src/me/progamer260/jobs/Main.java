package me.progamer260.jobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static me.progamer260.jobs.JobConfig.loadConfig;

public class Main extends JavaPlugin {


    private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("JobAPI");

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "JobAPI loaded!");
        getServer().getPluginManager().registerEvents(new JobEventHandler(), this);
        getCommand("job").setExecutor(new JobCommands());
        getCommand("jobs_list").setExecutor(new JobCommands());
        getCommand("jb").setExecutor(new JobCommands());
        createConfig();

        for (Player player: Bukkit.getOnlinePlayers()) {
            if (!plugin.getConfig().contains(player.getName() + ".hasJob")) {
                plugin.getConfig().set(player.getName() + ".hasJob", false);
            }
        }
    }

    private void createConfig() {
        loadConfig();
        plugin.saveConfig();
    }
    public void onDisable() {
        saveConfig();
    }
}
