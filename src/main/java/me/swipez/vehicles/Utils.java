package me.swipez.vehicles;

import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Utils {

    public static String convertToString(Vector vector){
        return vector.getX() + ";" + vector.getY() + ";" + vector.getZ();
    }

    public static String convertToString(EulerAngle eulerAngle){
        return eulerAngle.getX() + ";" + eulerAngle.getY() + ";" + eulerAngle.getZ();
    }
}
