package ua.ucu.apps.lab_6.flower.store;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import ua.ucu.apps.lab_6.delivery.Delivery;

@Data
public class Order {
    private List<Item>items;
    private Delivery delivery;

    public Order() {
        this.items = new ArrayList<>();
    }

    public double getOrderFinalPrice() {
        double finalPrice = items.stream()
            .mapToDouble(Item::getPrice)
            .sum();
        
        finalPrice += delivery.delivery(finalPrice);
        return finalPrice;
    }

}
