package ua.ucu.apps.lab_6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ucu.apps.lab_6.delivery.DHLDeliveryStrategy;
import ua.ucu.apps.lab_6.delivery.PostalDeliveryStrategy;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @PostMapping("/dhl")
    public ResponseEntity<?> calculateDHLDelivery(@RequestParam double orderPrice) {
        if (orderPrice <= 0) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Order price must be positive"));
        }

        DHLDeliveryStrategy strategy = new DHLDeliveryStrategy();
        double deliveryCost = strategy.delivery(orderPrice);

        DeliveryResponse response = new DeliveryResponse(
            orderPrice,
            deliveryCost,
            orderPrice + deliveryCost,
            "DHL",
            getDHLDescription(orderPrice)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/postal")
    public ResponseEntity<?> calculatePostalDelivery(@RequestParam double orderPrice) {
        if (orderPrice <= 0) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Order price must be positive"));
        }

        PostalDeliveryStrategy strategy = new PostalDeliveryStrategy();
        double deliveryCost = strategy.delivery(orderPrice);

        DeliveryResponse response = new DeliveryResponse(
            orderPrice,
            deliveryCost,
            orderPrice + deliveryCost,
            "POSTAL",
            getPostalDescription(orderPrice, deliveryCost)
        );

        return ResponseEntity.ok(response);
    }

    private String getDHLDescription(double orderPrice) {
        if (orderPrice >= 1500) {
            return "Reduced delivery cost for orders >= 1500";
        } else {
            return "Standard DHL delivery";
        }
    }

    private String getPostalDescription(double orderPrice, double cost) {
        if (orderPrice > 2000) {
            return "Free delivery for orders > 2000";
        } else if (orderPrice >= 1000) {
            return "Reduced postal delivery for orders 1000-2000";
        } else {
            return "Standard postal delivery";
        }
    }

    // Response DTOs
    public static class DeliveryResponse {
        private double orderPrice;
        private double deliveryCost;
        private double totalPrice;
        private String deliveryType;
        private String description;

        public DeliveryResponse(double orderPrice, double deliveryCost, 
                              double totalPrice, String deliveryType, 
                              String description) {
            this.orderPrice = orderPrice;
            this.deliveryCost = deliveryCost;
            this.totalPrice = totalPrice;
            this.deliveryType = deliveryType;
            this.description = description;
        }

        public double getOrderPrice() { return orderPrice; }
        public double getDeliveryCost() { return deliveryCost; }
        public double getTotalPrice() { return totalPrice; }
        public String getDeliveryType() { return deliveryType; }
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