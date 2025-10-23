package ua.ucu.apps.lab_6.payment;

public class CreditCardPaymentStrategy implements Payment {

    @Override
    public double payment(double orderPrice) {

        if (orderPrice >= 1000) {
            System.out.println("Payment by credit card, sale 5% for big order");
            return orderPrice * 0.95;
        }
        System.out.println("Payment by credit card, additional loaning 1%");
        return orderPrice * 1.1;
    }
    
}
