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

    public static Vector convertToVector(String string){
        String[] split = string.split(";");
        return new Vector(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
    }
}
