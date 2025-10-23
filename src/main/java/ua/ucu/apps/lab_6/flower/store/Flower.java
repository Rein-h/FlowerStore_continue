package ua.ucu.apps.lab_6.flower.store;

import lombok.Getter;
import lombok.Setter;



@Setter
public class Flower {
    @Getter
    private double sepalLength;
    private FlowerColor color;
    @Getter
    private double price;
    @Getter
    private FlowerType flowerType;

    public Flower() {
        sepalLength = 0;
        price = 0;
    }

    public Flower(Flower flower) {
        sepalLength = flower.sepalLength;
        color = flower.color;
        price = flower.price;
        flowerType = flower.flowerType;
    }

    public Flowermatching tosearch() {
        return new Flowermatching(color, flowerType);
    }

    public String getColor() {
        return color.toString();
    }
}
