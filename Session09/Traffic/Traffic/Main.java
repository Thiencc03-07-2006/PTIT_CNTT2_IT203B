package JavaAdvanced.Ss9.Traffic;

import JavaAdvanced.Ss9.Traffic.engine.Intersection;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  HỆ THỐNG MÔ PHỎNG GIAO THÔNG MINH     ║");
        System.out.println("╚════════════════════════════════════════╝");

        Intersection intersection = new Intersection();
        intersection.startSimulation();
    }
}