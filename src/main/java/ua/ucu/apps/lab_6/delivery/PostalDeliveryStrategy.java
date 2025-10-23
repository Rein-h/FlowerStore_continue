package ua.ucu.apps.lab_6.delivery;

public class PostalDeliveryStrategy implements Delivery {
    
    @Override
    public double delivery(double orderPrice) {

        System.out.println("Postal delivery");
        if (orderPrice >= 1000 && orderPrice <= 2000) {
            return 50;
        }
        else if (orderPrice > 2000) {
            return 0;
        }
        return 100;
    }
}
