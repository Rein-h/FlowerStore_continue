package ua.ucu.apps.lab_6.flower.store;

public class Flowermatching {
    private FlowerColor color;
    private FlowerType flower;

    public Flowermatching(FlowerColor col, FlowerType type) {
        this.color = col;
        this.flower = type;
    }

    public boolean sameflower(Flowermatching searched) {
        return color.equals(searched.color) && flower.equals(searched.flower);
    }

}
