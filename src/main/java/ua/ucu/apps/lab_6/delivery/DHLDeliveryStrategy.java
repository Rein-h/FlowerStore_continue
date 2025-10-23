package ua.ucu.apps.lab_6.delivery;

public class DHLDeliveryStrategy implements Delivery {
    
    public double delivery(double orderPrice) {

        if (orderPrice >= 1500) {
            return 50;
        }

        return 70;
    }

}
