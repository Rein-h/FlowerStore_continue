package ua.ucu.apps.lab_6.flower.store;

public class FlowerPack {
    private Flower flower;
    private int amount;

    public FlowerPack(Flower nFlower, int nAmount) {
        flower = nFlower;
        amount = nAmount;
    }

    public FlowerPack() {
        flower = new Flower();
        amount = 1;
    }

    public double getPrice() {
        return amount * flower.getPrice();
    }
}
