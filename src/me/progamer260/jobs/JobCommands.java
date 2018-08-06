package me.progamer260.jobs;

import me.progamer260.jobapi.JobType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static me.progamer260.jobapi.JobHandler.setJobTypeAsString;

public class JobCommands implements CommandExecutor {

    private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("JobAPI");

    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Boolean hasJob = plugin.getConfig().getBoolean(player.getName() + "." + "hasJob");

        //Info command
        if (command.getName().equalsIgnoreCase("jb")) {
            sender.sendMessage(ChatColor.GOLD + "###################");
            sender.sendMessage(ChatColor.AQUA + "Jobs Plugin");
            sender.sendMessage(ChatColor.AQUA + "By: Progamer260");
            sender.sendMessage(ChatColor.AQUA + "Version: 1.0.2");
            sender.sendMessage(ChatColor.GOLD + "###################");
        }
        //Core command
        if (command.getName().equalsIgnoreCase("job")) {
            //System.out.println("Pass Test 1");
            if (args.length >= 2) {
                //System.out.println("Pass Test 2");
                if (args[0].equalsIgnoreCase("choose")) {
                    if (hasJob) {
                        if (!player.hasPermission("jobs.changejob")) {
                            player.sendMessage(ChatColor.RED + "You do not have permission to change your job!");
                        }
                    } else {
                        System.out.println("Pass Test 3");
                        try {
                            JobType type = JobType.valueOf(args[1].toUpperCase());
                            //Enum value exists if the code has reached this point
                            player.sendMessage(ChatColor.GREEN + "Job " + type + " chosen!");
                            plugin.getConfig().set(player.getName() + ".hasJob", true);
                            setJobTypeAsString(player, type);
                            plugin.saveConfig();
                        } catch (IllegalArgumentException e) {
                            //Enum value does not exist if the code has reached this point
                            player.sendMessage(ChatColor.RED + "Must be " + JobType.values().toString());
                        }
                    }
                }
            }
        }
        if (command.getName().equalsIgnoreCase("jobs_list")) {
            JobType[] jobTypes = JobType.values();
            for (JobType jobType : jobTypes) {
                if (sender instanceof Player) {
                    player.sendMessage(ChatColor.GREEN + jobType.toString());
                } else if (sender instanceof ConsoleCommandSender) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + jobType.toString());
                }
            }
        }
        return true;
    }
}