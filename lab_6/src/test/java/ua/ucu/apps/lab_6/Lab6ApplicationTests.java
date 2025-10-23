package ua.ucu.apps.lab_6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import ua.ucu.apps.lab_6.payment.CreditCardPaymentStrategy;
import ua.ucu.apps.lab_6.payment.PayPalPaymentStrategy;
import ua.ucu.apps.lab_6.delivery.DHLDeliveryStrategy;
import ua.ucu.apps.lab_6.delivery.PostalDeliveryStrategy;

@DisplayName("Payment and Delivery Strategy Tests")
class PaymentAndDeliveryStrategyTest {

    private CreditCardPaymentStrategy creditCardPayment;
    private PayPalPaymentStrategy payPalPayment;
    private DHLDeliveryStrategy dhlDelivery;
    private PostalDeliveryStrategy postalDelivery;

    @BeforeEach
    void setUp() {
        creditCardPayment = new CreditCardPaymentStrategy();
        payPalPayment = new PayPalPaymentStrategy();
        dhlDelivery = new DHLDeliveryStrategy();
        postalDelivery = new PostalDeliveryStrategy();
    }

    // ==================== PAYMENT TESTS ====================

    @Test
    @DisplayName("Credit Card: Big order (>=1000) should have 5% discount")
    void testCreditCardPayment_BigOrder_WithDiscount() {
        double orderPrice = 1500.0;
        double result = creditCardPayment.payment(orderPrice);
        
        assertEquals(1425.0, result, 0.01, 
            "Big orders (>=1000) should have 5% discount");
    }

    @Test
    @DisplayName("Credit Card: Order of exactly 1000 should have 5% discount")
    void testCreditCardPayment_ExactlyThousand_WithDiscount() {
        double orderPrice = 1000.0;
        double result = creditCardPayment.payment(orderPrice);
        
        assertEquals(950.0, result, 0.01, 
            "Order of exactly 1000 should have 5% discount");
    }

    @Test
    @DisplayName("Credit Card: Small order (<1000) should have 10% fee")
    void testCreditCardPayment_SmallOrder_WithFee() {
        double orderPrice = 500.0;
        double result = creditCardPayment.payment(orderPrice);
        
        assertEquals(550.0, result, 0.01, 
            "Small orders (<1000) should have 10% fee");
    }

    @Test
    @DisplayName("Credit Card: Very small order should have 10% fee")
    void testCreditCardPayment_VerySmallOrder() {
        double orderPrice = 100.0;
        double result = creditCardPayment.payment(orderPrice);
        
        assertEquals(110.0, result, 0.01);
    }

    @Test
    @DisplayName("Credit Card: Large order should have 5% discount")
    void testCreditCardPayment_LargeOrder() {
        double orderPrice = 5000.0;
        double result = creditCardPayment.payment(orderPrice);
        
        assertEquals(4750.0, result, 0.01);
    }

    @Test
    @DisplayName("PayPal: Should always have 3% discount")
    void testPayPalPayment_StandardDiscount() {
        double orderPrice = 1000.0;
        double result = payPalPayment.payment(orderPrice);
        
        assertEquals(970.0, result, 0.01, 
            "PayPal should always have 3% discount");
    }

    @Test
    @DisplayName("PayPal: Small order with 3% discount")
    void testPayPalPayment_SmallOrder() {
        double orderPrice = 100.0;
        double result = payPalPayment.payment(orderPrice);
        
        assertEquals(97.0, result, 0.01);
    }

    @Test
    @DisplayName("PayPal: Large order with 3% discount")
    void testPayPalPayment_LargeOrder() {
        double orderPrice = 5000.0;
        double result = payPalPayment.payment(orderPrice);
        
        assertEquals(4850.0, result, 0.01);
    }

    @Test
    @DisplayName("PayPal: Decimal price handling")
    void testPayPalPayment_DecimalPrice() {
        double orderPrice = 123.45;
        double result = payPalPayment.payment(orderPrice);
        
        assertEquals(119.7465, result, 0.01);
    }

    @Test
    @DisplayName("Credit Card: Boundary case 999 should have fee")
    void testCreditCardPayment_BoundaryCase_999() {
        double orderPrice = 999.0;
        double result = creditCardPayment.payment(orderPrice);
        
        assertEquals(1098.9, result, 0.01, 
            "999 is less than 1000, should have fee");
    }

    @Test
    @DisplayName("Credit Card: Boundary case 1001 should have discount")
    void testCreditCardPayment_BoundaryCase_1001() {
        double orderPrice = 1001.0;
        double result = creditCardPayment.payment(orderPrice);
        
        assertEquals(950.95, result, 0.01, 
            "1001 is >= 1000, should have discount");
    }

    // ==================== DELIVERY TESTS ====================

    @Test
    @DisplayName("DHL: Big order (>=1500) should cost 50")
    void testDHLDelivery_BigOrder_ReducedCost() {
        double orderPrice = 2000.0;
        double result = dhlDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01, 
            "Orders >= 1500 should have delivery cost of 50");
    }

    @Test
    @DisplayName("DHL: Order of exactly 1500 should cost 50")
    void testDHLDelivery_ExactlyThreshold() {
        double orderPrice = 1500.0;
        double result = dhlDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01, 
            "Order of exactly 1500 should have delivery cost of 50");
    }

    @Test
    @DisplayName("DHL: Small order (<1500) should cost 70")
    void testDHLDelivery_SmallOrder_StandardCost() {
        double orderPrice = 1000.0;
        double result = dhlDelivery.delivery(orderPrice);
        
        assertEquals(70.0, result, 0.01, 
            "Orders < 1500 should have delivery cost of 70");
    }

    @Test
    @DisplayName("DHL: Very small order should cost 70")
    void testDHLDelivery_VerySmallOrder() {
        double orderPrice = 100.0;
        double result = dhlDelivery.delivery(orderPrice);
        
        assertEquals(70.0, result, 0.01);
    }

    @Test
    @DisplayName("DHL: Very large order should cost 50")
    void testDHLDelivery_VeryLargeOrder() {
        double orderPrice = 10000.0;
        double result = dhlDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01);
    }

    @Test
    @DisplayName("DHL: Boundary case 1499 should cost 70")
    void testDHLDelivery_BoundaryCase_1499() {
        double orderPrice = 1499.0;
        double result = dhlDelivery.delivery(orderPrice);
        
        assertEquals(70.0, result, 0.01, 
            "1499 is less than 1500, should cost 70");
    }

    @Test
    @DisplayName("DHL: Boundary case 1501 should cost 50")
    void testDHLDelivery_BoundaryCase_1501() {
        double orderPrice = 1501.0;
        double result = dhlDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01, 
            "1501 is >= 1500, should cost 50");
    }

    @Test
    @DisplayName("Postal: Very small order (<1000) should cost 100")
    void testPostalDelivery_VerySmallOrder() {
        double orderPrice = 500.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(100.0, result, 0.01, 
            "Orders < 1000 should have delivery cost of 100");
    }

    @Test
    @DisplayName("Postal: Medium order (1000-2000) should cost 50")
    void testPostalDelivery_MediumOrder_ReducedCost() {
        double orderPrice = 1500.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01, 
            "Orders between 1000 and 2000 should have delivery cost of 50");
    }

    @Test
    @DisplayName("Postal: Large order (>2000) should be free")
    void testPostalDelivery_LargeOrder_FreeDelivery() {
        double orderPrice = 3000.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(0.0, result, 0.01, 
            "Orders > 2000 should have free delivery");
    }

    @Test
    @DisplayName("Postal: Order of exactly 1000 should cost 50")
    void testPostalDelivery_ExactlyThousand() {
        double orderPrice = 1000.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01, 
            "Order of exactly 1000 should cost 50");
    }

    @Test
    @DisplayName("Postal: Order of exactly 2000 should cost 50")
    void testPostalDelivery_ExactlyTwoThousand() {
        double orderPrice = 2000.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01, 
            "Order of exactly 2000 should cost 50");
    }

    @Test
    @DisplayName("Postal: Order of 2001 should be free")
    void testPostalDelivery_JustOverTwoThousand() {
        double orderPrice = 2001.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(0.0, result, 0.01, 
            "Order of 2001 should have free delivery");
    }

    @Test
    @DisplayName("Postal: Very large order should be free")
    void testPostalDelivery_VeryLargeOrder() {
        double orderPrice = 10000.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(0.0, result, 0.01);
    }

    @Test
    @DisplayName("Postal: Boundary case 999 should cost 100")
    void testPostalDelivery_BoundaryCase_999() {
        double orderPrice = 999.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(100.0, result, 0.01, 
            "999 is less than 1000, should cost 100");
    }

    @Test
    @DisplayName("Postal: Boundary case 1001 should cost 50")
    void testPostalDelivery_BoundaryCase_1001() {
        double orderPrice = 1001.0;
        double result = postalDelivery.delivery(orderPrice);
        
        assertEquals(50.0, result, 0.01, 
            "1001 is between 1000 and 2000, should cost 50");
    }
}