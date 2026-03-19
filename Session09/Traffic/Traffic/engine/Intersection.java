package JavaAdvanced.Ss9.Traffic.engine;

import JavaAdvanced.Ss9.Traffic.entity.Direction;
import JavaAdvanced.Ss9.Traffic.entity.PriorityVehicle;
import JavaAdvanced.Ss9.Traffic.entity.StandardVehicle;
import JavaAdvanced.Ss9.Traffic.entity.Vehicle;
import JavaAdvanced.Ss9.Traffic.exception.CollisionException;
import JavaAdvanced.Ss9.Traffic.exception.TrafficJamException;
import JavaAdvanced.Ss9.Traffic.pattern.factory.VehicleFactory;
import JavaAdvanced.Ss9.Traffic.pattern.state.TrafficLightContext;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Intersection {
    private final TrafficLightContext trafficLight;
    private final BlockingQueue<Vehicle> vehicleQueue;
    private final Map<Direction, Queue<Vehicle>> waitingLanes;
    private final ReentrantLock intersectionLock;
    private final AtomicInteger vehiclesPassed;
    private final AtomicInteger trafficJamCount;
    private static final int MAX_QUEUE_SIZE = 10;
    private volatile boolean isRunning = true;
    private ExecutorService vehicleExecutor;
    private ScheduledExecutorService generator;
    private List<Thread> vehicleThreads;

    public Intersection() {
        this.trafficLight = new TrafficLightContext();
        this.vehicleQueue = new LinkedBlockingQueue<>();
        this.waitingLanes = new ConcurrentHashMap<>();
        this.intersectionLock = new ReentrantLock(true);
        this.vehiclesPassed = new AtomicInteger(0);
        this.trafficJamCount = new AtomicInteger(0);
        this.vehicleExecutor = Executors.newSingleThreadExecutor();
        this.vehicleThreads = new ArrayList<>();

        for (Direction dir : Direction.values()) {
            waitingLanes.put(dir, new ConcurrentLinkedQueue<>());
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleQueue.offer(vehicle);
        System.out.println(String.format("Phuong tien moi: %s #%s huong %s",
                vehicle.getType(), vehicle.getId(), vehicle.getDirection().getVietnamese()));
    }

    public void processVehicles() {
        while (isRunning) {
            try {
                Vehicle vehicle = vehicleQueue.poll(1, TimeUnit.SECONDS);
                if (vehicle != null) {
                    Thread vehicleThread = new Thread(vehicle);
                    vehicleThread.setDaemon(true);
                    vehicleThread.start();
                    vehicleThreads.add(vehicleThread);

                    navigateIntersection(vehicle);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (TrafficJamException e) {
                System.err.println(e.getMessage());
                trafficJamCount.incrementAndGet();
            } catch (CollisionException e) {
                System.err.println("Canh bao va cham: " + e.getMessage());
            }
        }
    }

    private void navigateIntersection(Vehicle vehicle) throws TrafficJamException, CollisionException, InterruptedException {
        Direction dir = vehicle.getDirection();
        Queue<Vehicle> lane = waitingLanes.get(dir);

        if (lane.size() >= MAX_QUEUE_SIZE) {
            vehicle.stopVehicle();
            throw new TrafficJamException(String.format(
                    "Ket xe: Lan duong %s da day voi %d xe dang cho!",
                    dir.getVietnamese(), lane.size()));
        }

        lane.add(vehicle);
        System.out.println(String.format("%s #%s vao lan cho huong %s",
                vehicle.getType(), vehicle.getId(), dir.getVietnamese()));

        try {
            if (vehicle instanceof PriorityVehicle) {
                handleEmergencyVehicle(vehicle);
                return;
            }

            int waitTime = 0;
            while (isRunning && !trafficLight.canPass(dir)) {
                if (waitTime >= 30) {
                    System.out.println(String.format("%s #%s bo qua do qua thoi gian cho",
                            vehicle.getType(), vehicle.getId()));
                    vehicle.stopVehicle();
                    return;
                }

                System.out.println(String.format("%s #%s dang cho den %s tai huong %s",
                        vehicle.getType(), vehicle.getId(),
                        trafficLight.getCurrentColor(), dir.getVietnamese()));
                vehicle.stop();
                Thread.sleep(1000);
                waitTime++;
            }

            if (isRunning && trafficLight.canPass(dir)) {
                if (vehicle instanceof StandardVehicle) {
                    ((StandardVehicle) vehicle).resume();
                }

                if (intersectionLock.tryLock(5, TimeUnit.SECONDS)) {
                    try {
                        vehicle.move();
                        vehiclesPassed.incrementAndGet();
                        System.out.println(String.format("%s #%s da qua nga tu",
                                vehicle.getType(), vehicle.getId()));
                    } finally {
                        intersectionLock.unlock();
                    }
                }
            }

        } finally {
            lane.remove(vehicle);
        }
    }

    private void handleEmergencyVehicle(Vehicle vehicle) {
        System.out.println(String.format("Xe cuu thuong #%s duoc uu tien!", vehicle.getId()));

        if (intersectionLock.tryLock()) {
            try {
                vehicle.emergencyOverride();
                vehiclesPassed.incrementAndGet();
            } finally {
                intersectionLock.unlock();
            }
        } else {
            vehicle.emergencyOverride();
            vehiclesPassed.incrementAndGet();
        }
    }

    public void startSimulation() {
        System.out.println("BAT DAU MO PHONG GIAO THONG");
        System.out.println("============================");

        vehicleExecutor.submit(this::processVehicles);

        generator = Executors.newSingleThreadScheduledExecutor();
        generator.scheduleAtFixedRate(() -> {
            if (isRunning) {
                addVehicle(VehicleFactory.createRandomVehicle());
            }
        }, 2, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        stopSimulation();
    }

    public void stopSimulation() {
        isRunning = false;

        for (Queue<Vehicle> lane : waitingLanes.values()) {
            for (Vehicle v : lane) {
                v.stopVehicle();
            }
            lane.clear();
        }

        for (Vehicle v : vehicleQueue) {
            v.stopVehicle();
        }
        vehicleQueue.clear();

        for (Thread t : vehicleThreads) {
            try {
                t.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (generator != null) {
            generator.shutdownNow();
        }

        try {
            vehicleExecutor.shutdownNow();
            vehicleExecutor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        trafficLight.stop();
        printStatistics();
    }

    private void printStatistics() {
        System.out.println("\nTHONG KE MO PHONG");
        System.out.println("==================");
        System.out.println("Xe da qua nga tu: " + vehiclesPassed.get());
        System.out.println("So lan ket xe: " + trafficJamCount.get());
    }
}