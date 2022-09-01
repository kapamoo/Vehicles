package me.swipez.vehicles.commands;

import me.swipez.vehicles.ArmorStandCreation;
import me.swipez.vehicles.Vehicle;
import me.swipez.vehicles.Vehicles;
import me.swipez.vehicles.VehiclesPlugin;
import me.swipez.vehicles.items.ItemRegistry;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class VehicleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player){
            if (!player.hasPermission("vehicles.admin")){
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                return true;
            }
            if (args.length == 2){
                if (args[0].equals("load")){
                    String name = args[1];
                    Vehicles vehicles = null;
                    try {
                        vehicles = Vehicles.valueOf(name.toUpperCase());
                    } catch (IllegalArgumentException e){
                        player.sendMessage(ChatColor.RED+"Vehicle not found, resorting to config names");
                        return true;
                    }
                    ArmorStandCreation.load(name, player.getLocation().clone(), vehicles, player.getUniqueId());
                }
            }
            else if (args.length == 1){
                if (args[0].equals("give")){
                    player.getInventory().addItem(ItemRegistry.MOTORCYCLE_BOX.getItemStack());
                    player.getInventory().addItem(ItemRegistry.MONSTER_TRUCK_BOX.getItemStack());
                    player.getInventory().addItem(ItemRegistry.NORMAL_BOX.getItemStack());
                    player.getInventory().addItem(ItemRegistry.VAN_BOX.getItemStack());
                    player.getInventory().addItem(ItemRegistry.HOTROD_BOX.getItemStack());
                    player.getInventory().addItem(ItemRegistry.FAMILY_BOX.getItemStack());
                    player.getInventory().addItem(ItemRegistry.FORMULA_BOX.getItemStack());
                    player.getInventory().addItem(ItemRegistry.TOASTER_BOX.getItemStack());
                }
                if (args[0].equals("clear")){
                    for (UUID uuid : VehiclesPlugin.vehicles.keySet()){
                        VehiclesPlugin.vehicles.get(uuid).remove(true);
                    }
                }
            }
        }
        return true;
    }
}
