package me.progamer260.jobs;

import me.progamer260.jobapi.JobType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.material.Crops;
import org.bukkit.plugin.Plugin;

import static me.progamer260.jobapi.JobHandler.getJobAsString;

public class JobEventHandler implements Listener {
    private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("JobAPI");

    @EventHandler
    public void onJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.getConfig().getConfigurationSection(player.getName());
        if (!plugin.getConfig().contains(player.getName() + ".hasJob")) {
            plugin.getConfig().set(player.getName() + ".hasJob", false);
            plugin.saveConfig();
        }
    }

    @EventHandler
    public void blockBreak (BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        Material material = block.getType();
        //Farmer
        if (block instanceof Crops) {
            if (!getJobAsString(player).equals(JobType.FARMER)) {
                player.sendMessage(ChatColor.RED + "You must be a farmer to harvest crops!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onTeleport (PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location location = event.getTo();

        //Mining World
        if (location.getX() == plugin.getConfig().getInt("MiningWorld.X")) {
            if (location.getY() == plugin.getConfig().getInt("MiningWorld.Y")) {
                if (location.getZ() == plugin.getConfig().getInt("MiningWorld.Z")) {
                    if (!getJobAsString(player).equals(JobType.MINER)) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "You must be a miner to enter the mines!");
                    } else {
                        player.sendMessage(ChatColor.GREEN + "Welcome to the Mines miner!");
                    }
                }
            }
        }

        //Lumber World
        if (location.getX() == plugin.getConfig().getInt("LumberWorld.X")) {
            if (location.getY() == plugin.getConfig().getInt("LumberWorld.Y")) {
                if (location.getZ() == plugin.getConfig().getInt("LumberWorld.Z")) {
                    if (!getJobAsString(player).equals(JobType.MINER)) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "You must be a Lumberjack to enter the forrest!");
                    } else {
                        player.sendMessage(ChatColor.GREEN + "Welcome to the Forrest lumberjack!");
                    }
                }
            }
        }
    }


        //TODO Finish Crafting Jobs
        @EventHandler
        private void onCraft (CraftItemEvent event){
        /*Player player = (Player) event.getWhoClicked();
        Recipe recipe = event.getRecipe();
        CraftingInventory craftingInventory = event.getInventory();
        //Cook
        if (recipe.getResult().equals(Material.COOKIE)) {
            if (!getJobAsString(player).equals(JobType.COOK)) {
                player.sendMessage(ChatColor.RED + "You must be a cook to craft Cookies!");
                event.setCancelled(true);
            }
        }
        if (recipe.getResult().equals(Material.CAKE)) {
            if (!getJobAsString(player).equals(JobType.COOK)) {
                player.sendMessage(ChatColor.RED + "You must be a cook to craft Cake!");
                event.setCancelled(true);
            }
        }
        if (recipe.getResult().equals(Material.PUMPKIN_PIE)) {
            if (!getJobAsString(player).equals(JobType.COOK)) {
                player.sendMessage(ChatColor.RED + "You must be a cook to craft Pie!");
                event.setCancelled(true);
            }
        }
        if (recipe.getResult().equals(Material.RABBIT_STEW)) {
            if (!getJobAsString(player).equals(JobType.COOK)) {
                player.sendMessage(ChatColor.RED + "You must be a cook to craft Stew!!");
                event.setCancelled(true);
            }
        }

        //Fletcher
        if (event.getClickedInventory() instanceof CraftingInventory) {
            if (craftingInventory.contains(Material.LEATHER)) {
                if (!getJobAsString(player).equals(JobType.FLETCHER)) {
                    player.sendMessage(ChatColor.RED + "You must be a fletcher to craft with leather!");
                    event.setCancelled(true);
                }
            }
        }
        if (recipe.getResult().equals(Material.BOW) || recipe.getResult().equals(Material.ARROW)) {
            if (!getJobAsString(player).equals(JobType.FLETCHER)) {
                player.sendMessage(ChatColor.RED + "You must be a fletcher to craft a bow or arrow!");
            }
        }*/


        }
    }