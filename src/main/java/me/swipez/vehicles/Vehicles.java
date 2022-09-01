package me.swipez.vehicles;

public enum Vehicles {
    MOTORCYCLE("motorcycle", 0.5, 0.05, 0.05, 1),
    FORMULA_CAR("formula_car", 0.6, 0.2, 0.08, 1),
    MONSTER_TRUCK("monster_truck", 0.6, 0.1, 0.005, 1),
    NORMAL_CAR("normal_car", 0.3, 0.1, 0.01, 1),
    TOASTER("toaster", 0.7, 0.2, 0.0005, 1),
    VAN("van", 0.35, 0.03, 0.01, 1),
    HOTROD("hotrod", 0.7, 0.15, 0.05, 1),
    FAMILY_CAR("family_car", 0.35, 0.08, 0.01, 1)
    ;

    public String carName;
    public double speed;
    public double turnRate;
    public double gainRate;
    public double stepHeight;

    Vehicles(String name, double speed, double turnRate, double gainRate, double stepHeight) {
        this.carName = name;
        this.speed = speed;
        this.turnRate = turnRate;
        this.gainRate = gainRate;
        this.stepHeight = stepHeight;
    }
}
