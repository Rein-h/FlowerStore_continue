package ua.ucu.apps.lab_6.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.ucu.apps.lab_6.flower.store.Flower;
import ua.ucu.apps.lab_6.flower.store.FlowerColor;
import ua.ucu.apps.lab_6.flower.store.FlowerType;

@RestController
public class FlowerController {

    @RequestMapping("/Flower")
    public List<Flower> getFlowers() {
        Flower flower = new Flower();
        flower.setColor(FlowerColor.BLUE);
        flower.setFlowerType(FlowerType.TULIP);
        flower.setPrice(120);
        flower.setSepalLength(25);
        return List.of(new Flower(flower));
    }

}
