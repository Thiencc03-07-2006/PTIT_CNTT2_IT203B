package JavaAdvanced.Ss9.Traffic.pattern.factory;

import JavaAdvanced.Ss9.Traffic.entity.*;
import java.util.Random;

public class VehicleFactory {
    private static Random random = new Random();
    private static int vehicleCounter = 0;

    public static Vehicle createRandomVehicle() {
        vehicleCounter++;
        Direction[] directions = Direction.values();
        Direction randomDirection = directions[random.nextInt(directions.length)];

        if (random.nextInt(10) < 2) {
            return new PriorityVehicle(
                    String.valueOf(vehicleCounter),
                    "Xe cuu thuong",
                    2,
                    randomDirection
            );
        } else {
            String[] types = {"Xe con", "Xe tai", "Xe buyt", "Xe may"};
            String randomType = types[random.nextInt(types.length)];
            int speed = 1 + random.nextInt(3);

            return new StandardVehicle(
                    String.valueOf(vehicleCounter),
                    randomType,
                    speed,
                    randomDirection
            );
        }
    }
}