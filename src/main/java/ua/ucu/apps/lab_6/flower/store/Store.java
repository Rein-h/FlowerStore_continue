package ua.ucu.apps.lab_6.flower.store;

import java.util.ArrayList;

public class Store {
    private ArrayList<Flower> flowers;

    public Store() {
        flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public ArrayList<Flower> search(Flowermatching needed) {
        ArrayList<Flower> found = new ArrayList<>();

        for (Flower flower : flowers) {
            if (flower.tosearch().sameflower(needed)) {
                found.add(flower);
            }
        }
        return found;
    }    


}
