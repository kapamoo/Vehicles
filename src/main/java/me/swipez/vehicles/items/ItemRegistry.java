package me.swipez.vehicles.items;

import me.swipez.vehicles.VehicleType;
import me.swipez.vehicles.VehiclesPlugin;
import me.swipez.vehicles.recipe.VehicleBox;
import me.swipez.vehicles.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class ItemRegistry {

    public static ItemStack EQUIPMENT_GUI = ItemBuilder.of(Material.FLINT)
            .name(ChatColor.GREEN+"Equipment")
            .build();

    public static ItemStack CREATE_STAND = ItemBuilder.of(Material.ARMOR_STAND)
            .name(ChatColor.GREEN+"Create New Armor Stand")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack ROTATE_CONTROL = ItemBuilder.of(Material.FEATHER)
            .name(ChatColor.GREEN+"Rotate")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack MOVE_CONTROL = ItemBuilder.of(Material.GOLD_INGOT)
            .name(ChatColor.GREEN+"Move")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack WHEEL = ItemBuilder.of(Material.FLINT)
            .name(ChatColor.GRAY.toString()+ChatColor.ITALIC+ChatColor.BOLD+"Wheel")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack MOTOR = ItemBuilder.of(Material.OBSERVER)
            .name(ChatColor.GOLD.toString()+ChatColor.ITALIC+ChatColor.BOLD+"Motor")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack FLYING_MOTOR = ItemBuilder.of(Material.DROPPER)
            .name(ChatColor.WHITE.toString()+ChatColor.ITALIC+ChatColor.BOLD+"Flying Motor")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static VehicleBox MOTORCYCLE_BOX = new VehicleBox(VehicleType.MOTORCYCLE, "red", ChatColor.RED);
    public static VehicleBox FORMULA_BOX = new VehicleBox(VehicleType.FORMULA_CAR, "light_blue", ChatColor.BLUE);
    public static VehicleBox NORMAL_BOX = new VehicleBox(VehicleType.NORMAL_CAR, "yellow", ChatColor.YELLOW);
    public static VehicleBox MONSTER_TRUCK_BOX = new VehicleBox(VehicleType.MONSTER_TRUCK, "lime", ChatColor.GREEN);
    public static VehicleBox FAMILY_BOX = new VehicleBox(VehicleType.FAMILY_CAR, "pink", ChatColor.LIGHT_PURPLE);
    public static VehicleBox HOTROD_BOX = new VehicleBox(VehicleType.HOTROD, "white", ChatColor.WHITE);
    public static VehicleBox VAN_BOX = new VehicleBox(VehicleType.VAN, "black", ChatColor.DARK_GRAY);
    public static VehicleBox TOASTER_BOX = new VehicleBox(VehicleType.TOASTER, "white", ChatColor.WHITE);
    public static VehicleBox HELICOPTER_BOX = new VehicleBox(VehicleType.HELICOPTER, "green", ChatColor.DARK_GREEN);
    public static VehicleBox JET_BOX = new VehicleBox(VehicleType.JET, "gray", ChatColor.GRAY);
    public static VehicleBox PRIVATE_JET_BOX = new VehicleBox(VehicleType.PRIVATE_JET, "white", ChatColor.WHITE);
    public static VehicleBox BIPLANE_BOX = new VehicleBox(VehicleType.BIPLANE, "yellow", ChatColor.YELLOW);
    public static VehicleBox SPY_COPTER_BOX = new VehicleBox(VehicleType.SPY_COPTER, "red", ChatColor.RED);

    public static void registerRecipes(){
        registerFlyingMotorRecipe();
        registerWheelRecipe();
        registerMotorRecipe();
        registerMotorcycleRecipe();
        registerVanRecipe();
        registerFamilyCarRecipe();
        registerFormulaRecipe();
        registerMonsterTruckRecipe();
        registerHotRodRecipe();
        registerNormalCarRecipe();
        registerHelicopterRecipe();
        registerPrivateJetRecipe();
        registerJetRecipe();
        registerBiplaneRecipe();
        registerSpyCopterRecipe();
    }

    private static void registerJetRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "jet"), JET_BOX.getItemStack())
                .shape("IGI","RMR","WDW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(FLYING_MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('R', new RecipeChoice.MaterialChoice(Material.REDSTONE_BLOCK))
                .setIngredient('D', new RecipeChoice.MaterialChoice(Material.DIAMOND));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerHelicopterRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "helicopter"), HELICOPTER_BOX.getItemStack())
                .shape("CCC","GMG","WDW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(FLYING_MOTOR))
                .setIngredient('C', new RecipeChoice.MaterialChoice(Material.COPPER_INGOT))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('D', new RecipeChoice.MaterialChoice(Material.DIAMOND));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerSpyCopterRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "spy_copter"), SPY_COPTER_BOX.getItemStack())
                .shape("CCC","RMG","WDW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(FLYING_MOTOR))
                .setIngredient('C', new RecipeChoice.MaterialChoice(Material.COPPER_INGOT))
                .setIngredient('R', new RecipeChoice.MaterialChoice(Material.REDSTONE_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('D', new RecipeChoice.MaterialChoice(Material.DIAMOND));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerPrivateJetRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "private_jet"), PRIVATE_JET_BOX.getItemStack())
                .shape("II ","RMG","WDW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(FLYING_MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('R', new RecipeChoice.MaterialChoice(Material.REDSTONE_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('D', new RecipeChoice.MaterialChoice(Material.DIAMOND));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerBiplaneRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "biplane"), BIPLANE_BOX.getItemStack())
                .shape("III","RMG","WDW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(FLYING_MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('R', new RecipeChoice.MaterialChoice(Material.REDSTONE_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('D', new RecipeChoice.MaterialChoice(Material.DIAMOND));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerMotorcycleRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "motorcycle"), MOTORCYCLE_BOX.getItemStack())
                .shape("B  ","MII","W W")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('B', new RecipeChoice.MaterialChoice(Material.IRON_BARS));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerVanRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "van"), VAN_BOX.getItemStack())
                .shape("III","IIG","WMW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerFormulaRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "formula_car"), FORMULA_BOX.getItemStack())
                .shape("I  ","IGB","WMW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('B', new RecipeChoice.MaterialChoice(Material.GOLD_BLOCK));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerMonsterTruckRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "monster_truck"), MONSTER_TRUCK_BOX.getItemStack())
                .shape("IG ","IDM","W W")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('D', new RecipeChoice.MaterialChoice(Material.DIAMOND));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerNormalCarRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "normal_car"), NORMAL_BOX.getItemStack())
                .shape("IMG","W W")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerHotRodRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "hotrod"), HOTROD_BOX.getItemStack())
                .shape("G  ","IMI", "WDW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(MOTOR))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.IRON_NUGGET))
                .setIngredient('D', new RecipeChoice.MaterialChoice(Material.DIAMOND));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerFamilyCarRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "family_car"), FAMILY_BOX.getItemStack())
                .shape("GGM", "WBW")
                .setIngredient('W', new RecipeChoice.ExactChoice(WHEEL))
                .setIngredient('M', new RecipeChoice.ExactChoice(MOTOR))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GLASS))
                .setIngredient('B', new RecipeChoice.MaterialChoice(Material.GOLD_BLOCK));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerWheelRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "wheel"), WHEEL)
                .shape("III","ISI","III")
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_INGOT))
                .setIngredient('S', new RecipeChoice.MaterialChoice(Material.STICK));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerMotorRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "motor"), MOTOR)
                .shape("RHR","III","GGG")
                .setIngredient('R', new RecipeChoice.MaterialChoice(Material.REDSTONE))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GUNPOWDER))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('H', new RecipeChoice.MaterialChoice(Material.HOPPER));
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerFlyingMotorRecipe(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(VehiclesPlugin.getPlugin(), "flying_motor"), FLYING_MOTOR)
                .shape("III","FNF","GGG")
                .setIngredient('F', new RecipeChoice.MaterialChoice(Material.FEATHER))
                .setIngredient('G', new RecipeChoice.MaterialChoice(Material.GOLD_BLOCK))
                .setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
                .setIngredient('N', new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT));
        Bukkit.addRecipe(shapedRecipe);
    }
}
