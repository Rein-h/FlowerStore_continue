package ua.ucu.apps.lab_6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ucu.apps.lab_6.payment.CreditCardPaymentStrategy;
import ua.ucu.apps.lab_6.payment.PayPalPaymentStrategy;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/credit-card")
    public ResponseEntity<?> processCreditCardPayment(@RequestParam double orderPrice) {
        if (orderPrice <= 0) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Order price must be positive"));
        }

        CreditCardPaymentStrategy strategy = new CreditCardPaymentStrategy();
        double finalPrice = strategy.payment(orderPrice);

        PaymentResponse response = new PaymentResponse(
            orderPrice,
            finalPrice,
            "CREDIT_CARD",
            getDiscountDescription(orderPrice, finalPrice)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/paypal")
    public ResponseEntity<?> processPayPalPayment(@RequestParam double orderPrice) {
        if (orderPrice <= 0) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Order price must be positive"));
        }

        PayPalPaymentStrategy strategy = new PayPalPaymentStrategy();
        double finalPrice = strategy.payment(orderPrice);

        PaymentResponse response = new PaymentResponse(
            orderPrice,
            finalPrice,
            "PAYPAL",
            "3% discount applied"
        );

        return ResponseEntity.ok(response);
    }

    private String getDiscountDescription(double original, double final_) {
        if (original >= 1000) {
            return "5% discount for large order";
        } else {
            return "10% fee for small order";
        }
    }

    public static class PaymentResponse {
        private double originalPrice;
        private double finalPrice;
        private String paymentType;
        private String description;

        public PaymentResponse(double originalPrice, double finalPrice, 
                             String paymentType, String description) {
            this.originalPrice = originalPrice;
            this.finalPrice = finalPrice;
            this.paymentType = paymentType;
            this.description = description;
        }

        public double getOriginalPrice() { return originalPrice; }
        public double getFinalPrice() { return finalPrice; }
        public String getPaymentType() { return paymentType; }
        public String getDescription() { return description; }
    }

    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() { return error; }
    }
}