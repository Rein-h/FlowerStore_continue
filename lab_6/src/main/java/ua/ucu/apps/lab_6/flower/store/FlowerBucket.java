package ua.ucu.apps.lab_6.flower.store;

import java.util.ArrayList;

public class FlowerBucket {
    private ArrayList<FlowerPack> flowers;

    public FlowerBucket() {
        flowers = new ArrayList<>();
    }

    public FlowerBucket(ArrayList<FlowerPack> pack) {
        flowers = new ArrayList<>(pack);
    }
    
    public void addFlower(FlowerPack pack) {
        flowers.add(pack);
    }

    public double getPrice() {
        double sum = 0;
        for (FlowerPack pack : flowers) {
            sum += pack.getPrice();
        }

        return sum;
    }

}
