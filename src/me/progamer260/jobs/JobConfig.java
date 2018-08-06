package me.progamer260.jobs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public  class JobConfig  {
    private static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("JobAPI");
    private static FileConfiguration config = plugin.getConfig();

    public static void loadConfig() {
        //Mining World

        config.addDefault("MiningWorld.X", 0);
        config.addDefault("MiningWorld.Y", 0);
        config.addDefault("MiningWorld.Z", 0);

        //Lumber World

        config.addDefault("LumberWorld.X", 0);
        config.addDefault("LumberWorld.Y", 0);
        config.addDefault("LumberWorld.Z", 0);

        plugin.saveConfig();
        }
    }

