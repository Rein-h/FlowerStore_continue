package ua.ucu.apps.lab_6.payment;

public class PayPalPaymentStrategy implements Payment {

    public double payment(double orderPrice) {

        System.out.println("Payment by PayPal, sale 3%!");
        return orderPrice * 0.97;

    }
    
}
