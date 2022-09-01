package me.swipez.vehicles.gui;

import me.swipez.vehicles.*;
import me.swipez.vehicles.commands.ArmorStandMakeCommand;
import me.swipez.vehicles.commands.CreationModeCommand;
import me.swipez.vehicles.items.ItemRegistry;
import me.swipez.vehicles.recipe.VehicleBox;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GeneralListeners implements Listener {

    HashMap<UUID, EquipmentGUI> equipmentGUIHashMap = new HashMap<>();
    List<UUID> ignoredPlayers = new ArrayList<>();

    @EventHandler
    public void onSeatGetsHurt(EntityDamageEvent event){
        if (VehiclesPlugin.allSeats.contains(event.getEntity().getUniqueId())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerUsesStick(PlayerInteractEvent event){
        if (!VehiclesPlugin.creatorModeActive){
            return;
        }
        if (!event.hasBlock()){
            return;
        }
        if (!event.hasItem()){
            return;
        }
        if (!event.getItem().getType().equals(Material.STICK)){
            return;
        }
        // First corner must be < second corner
        if (event.getAction().toString().toLowerCase().contains("left")){
            ArmorStandMakeCommand.firstCorner = event.getClickedBlock().getLocation();
            event.getPlayer().sendMessage(ChatColor.GREEN+"First corner selected");
            event.setCancelled(true);
        }
        if (event.getAction().toString().toLowerCase().contains("right")){
            ArmorStandMakeCommand.secondCorner = event.getClickedBlock().getLocation();
            event.getPlayer().sendMessage(ChatColor.GREEN+"Second corner selected");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlacesBox(BlockPlaceEvent event){
        if (event.getItemInHand().isSimilar(ItemRegistry.MOTOR)){
            event.setCancelled(true);
        }
        VehicleBox vehicleBox = VehicleBox.getBox(event.getItemInHand(), ChatColor.WHITE);
        if (vehicleBox != null){
            Vehicles vehicle = vehicleBox.vehicle;
            event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
            Block block = event.getBlock();
            Vehicle spawned = ArmorStandCreation.load(vehicle.carName, block.getLocation().clone(), vehicle, event.getPlayer().getUniqueId());
            spawned.dye(vehicleBox.color);
            block.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, block.getLocation().clone(), 5);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractsWithStand(PlayerInteractAtEntityEvent event){
        if (event.getRightClicked() instanceof ArmorStand clickedStand){
            PersistentDataContainer persistentDataContainer = clickedStand.getPersistentDataContainer();
            if (persistentDataContainer.has(new NamespacedKey(VehiclesPlugin.getPlugin(), "vehicleId"), PersistentDataType.STRING)){
                UUID uuid = UUID.fromString(persistentDataContainer.get(new NamespacedKey(VehiclesPlugin.getPlugin(), "vehicleId"), PersistentDataType.STRING));
                if (VehiclesPlugin.vehicles.containsKey(uuid)){
                    Vehicle vehicle = VehiclesPlugin.vehicles.get(uuid);
                    if (vehicle.isOwnedBy(event.getPlayer())){
                        if (event.getPlayer().getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("_dye")){
                            String color = event.getPlayer().getInventory().getItemInMainHand().getType().toString().toLowerCase().replace("_dye", "");
                            vehicle.dye(color);
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
                            return;
                        }
                        if (event.getPlayer().isSneaking()){
                            VehicleBox vehicleBox = VehicleBox.vehicleBoxes.get(Vehicles.valueOf(vehicle.enumName));
                            VehicleBox coloredBox = vehicleBox.getAlternateColor(vehicle.color, ChatColor.WHITE);
                            vehicle.remove(true);
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
                            event.getPlayer().getInventory().addItem(coloredBox.getItemStack());
                            return;
                        }
                    }
                    vehicle.attemptSit(event.getPlayer());
                }
            }
        }
        process(event.getPlayer().getInventory().getItemInMainHand(), event.getPlayer(), event);
    }

    @EventHandler
    public void onPlayerInteractsWithItem(PlayerInteractEvent event){
        if (!event.hasItem()){
            return;
        }
        Player player = event.getPlayer();
        process(event.getItem(), player, event);
        ArmorStandCreation armorStandCreation = CreationModeCommand.creationHashMap.get(player.getUniqueId());
        if (armorStandCreation == null){
            return;
        }
        CreationSettings creationSettings = armorStandCreation.getSettings();
        if (ignoredPlayers.contains(player.getUniqueId())){
            return;
        }
        if (event.getAction().toString().toLowerCase().contains("left")){
            if (event.getItem().isSimilar(ItemRegistry.ROTATE_CONTROL)) {
                if (!player.isSneaking()) {
                    double moveamount = creationSettings.rotationAmount;
                    double xAmount = 0;
                    double yAmount = 0;
                    double zAmount = 0;
                    if (creationSettings.axis.equals("x")) {
                        xAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("y")) {
                        yAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("z")) {
                        zAmount = moveamount;
                    }

                    if (creationSettings.rotateBody) {
                        armorStandCreation.rotateTotal(xAmount, yAmount, zAmount);
                    } else if (creationSettings.rotateLeftArm) {
                        armorStandCreation.rotateArm(true, xAmount, yAmount, zAmount);
                    } else if (creationSettings.rotateRightArm) {
                        armorStandCreation.rotateArm(false, xAmount, yAmount, zAmount);
                    }
                    ignoredPlayers.add(player.getUniqueId());
                }
                else {
                    creationSettings.loopAxis();
                    player.sendMessage(ChatColor.GREEN+"Axis set to "+creationSettings.axis);
                    ignoredPlayers.add(player.getUniqueId());
                }
            }
            else if (event.getItem().isSimilar(ItemRegistry.MOVE_CONTROL)){
                if (!player.isSneaking()) {
                    double moveamount = creationSettings.movementAmount;
                    double xAmount = 0;
                    double yAmount = 0;
                    double zAmount = 0;
                    if (creationSettings.axis.equals("x")) {
                        xAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("y")) {
                        yAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("z")) {
                        zAmount = moveamount;
                    }
                    armorStandCreation.move(xAmount, yAmount, zAmount);

                    ignoredPlayers.add(player.getUniqueId());
                }
                else {
                    creationSettings.loopAxis();
                    player.sendMessage(ChatColor.GREEN+"Axis set to "+creationSettings.axis);
                    ignoredPlayers.add(player.getUniqueId());
                }
            }
        }
        else {
            if (event.getItem().isSimilar(ItemRegistry.ROTATE_CONTROL)) {
                if (!player.isSneaking()) {
                    double moveamount = -creationSettings.rotationAmount;
                    double xAmount = 0;
                    double yAmount = 0;
                    double zAmount = 0;
                    if (creationSettings.axis.equals("x")) {
                        xAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("y")) {
                        yAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("z")) {
                        zAmount = moveamount;
                    }

                    if (creationSettings.rotateBody) {
                        armorStandCreation.rotateTotal(xAmount, yAmount, zAmount);
                    } else if (creationSettings.rotateLeftArm) {
                        armorStandCreation.rotateArm(true, xAmount, yAmount, zAmount);
                    } else if (creationSettings.rotateRightArm) {
                        armorStandCreation.rotateArm(false, xAmount, yAmount, zAmount);
                    }
                    ignoredPlayers.add(player.getUniqueId());
                }
                else {
                    if (creationSettings.rotateBody){
                        creationSettings.setRotateRightArm();
                    }
                    else if (creationSettings.rotateRightArm){
                        creationSettings.setRotateLeftArm();
                    }
                    else if (creationSettings.rotateLeftArm){
                        creationSettings.setRotateBody();
                    }

                    // Send player a message telling them what they are now rotating
                    if (creationSettings.rotateBody){
                        player.sendMessage(ChatColor.GREEN+"Rotating body");
                    }
                    else if (creationSettings.rotateLeftArm){
                        player.sendMessage(ChatColor.GREEN+"Rotating left arm");
                    }
                    else if (creationSettings.rotateRightArm){
                        player.sendMessage(ChatColor.GREEN+"Rotating right arm");
                    }
                    ignoredPlayers.add(player.getUniqueId());
                }
            }
            else if (event.getItem().isSimilar(ItemRegistry.MOVE_CONTROL)){
                if (!player.isSneaking()) {
                    double moveamount = -creationSettings.movementAmount;
                    double xAmount = 0;
                    double yAmount = 0;
                    double zAmount = 0;
                    if (creationSettings.axis.equals("x")) {
                        xAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("y")) {
                        yAmount = moveamount;
                    }
                    if (creationSettings.axis.equals("z")) {
                        zAmount = moveamount;
                    }
                    armorStandCreation.move(xAmount, yAmount, zAmount);

                    ignoredPlayers.add(player.getUniqueId());
                }
                else {
                    creationSettings.loopAxis();
                    player.sendMessage(ChatColor.GREEN+"Axis set to "+creationSettings.axis);
                    ignoredPlayers.add(player.getUniqueId());
                }
            }
        }
        if (ignoredPlayers.contains(player.getUniqueId())){
            event.setCancelled(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    ignoredPlayers.remove(player.getUniqueId());
                }
            }.runTaskLater(VehiclesPlugin.getPlugin(), 1);
        }
    }

    private void process(ItemStack itemStack, Player player, Cancellable event){
        if (!CreationModeCommand.creationHashMap.containsKey(player.getUniqueId())){
            return;
        }
        ArmorStandCreation armorStandCreation = CreationModeCommand.creationHashMap.get(player.getUniqueId());
        if (itemStack.isSimilar(ItemRegistry.EQUIPMENT_GUI)){
            if (!equipmentGUIHashMap.containsKey(player.getUniqueId())){
                EquipmentGUI equipmentGUI = new EquipmentGUI(armorStandCreation, player.getUniqueId());
                equipmentGUIHashMap.put(player.getUniqueId(), equipmentGUI);
                equipmentGUI.display(player);
            }
            else {
                equipmentGUIHashMap.get(player.getUniqueId()).display(player);
            }
            event.setCancelled(true);
        }
        if (itemStack.isSimilar(ItemRegistry.CREATE_STAND)){
            armorStandCreation.makeArmorStand();
            event.setCancelled(true);
        }
    }
}
