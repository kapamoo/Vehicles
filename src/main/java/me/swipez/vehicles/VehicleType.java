package me.swipez.vehicles;

public enum VehicleType {
    MOTORCYCLE("motorcycle", 0.5, 0.05, 0.05, 1),
    FORMULA_CAR("formula_car", 0.6, 0.2, 0.08, 1),
    MONSTER_TRUCK("monster_truck", 0.6, 0.1, 0.005, 1),
    NORMAL_CAR("normal_car", 0.3, 0.1, 0.01, 1),
    TOASTER("toaster", 0.7, 0.2, 0.0005, 1),
    VAN("van", 0.35, 0.03, 0.01, 1),
    HOTROD("hotrod", 0.7, 0.15, 0.05, 1),
    FAMILY_CAR("family_car", 0.35, 0.08, 0.01, 1),
    HELICOPTER("helicopter", 0.5, 0.05, 0.004, 1, false, 0.3),
    JET("jet", 0.8, 0.1, 0.1, 1, false, 0.5),
    PRIVATE_JET("private_jet", 0.8, 0.1, 0.1, 1, true, 0.4),
    BIPLANE("biplane", 0.9, 0.1, 0.003, 1, true, 0.2),
    SPY_COPTER("spy_copter", 0.8, 0.1, 0.01, 1, false, 0.3)
    ;

    public String carName;
    public double speed = 0;
    public double turnRate = 0;
    public double gainRate = 0;
    public double stepHeight = 0;
    public double raiseRate = 0;
    public boolean rotateX = false;

    VehicleType(String name, double speed, double turnRate, double gainRate, double stepHeight) {
        this.carName = name;
        this.speed = speed;
        this.turnRate = turnRate;
        this.gainRate = gainRate;
        this.stepHeight = stepHeight;
    }

    VehicleType(String name, double speed, double turnRate, double gainRate, double stepHeight, boolean rotateX, double raiseRate) {
        this.carName = name;
        this.speed = speed;
        this.turnRate = turnRate;
        this.gainRate = gainRate;
        this.stepHeight = stepHeight;
        this.rotateX = rotateX;
        this.raiseRate = raiseRate;
    }
}
