package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import com.stripe.exception.StripeException;

@Service
public class PaymentGatewayService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public PaymentGatewayService() {
        Stripe.apiKey = "sk_test_51RupaeDs78UWAqSTXA6IBxZZZIzVMRhkeWP9hc2XUz4PRhOzbNCfRad1kToQ7Kv7LGZ3goX2Vnph0Eo0urpoFF9m00sAzINEIN"; // test secret key from Stripe dashboard
    }

    public String createPaymentIntent(double amount) throws StripeException {
        System.out.println("Entered the Payment Intent" );
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int)(amount * 100)); // amount in cents
        params.put("currency", "inr"); // or "inr"
        params.put("automatic_payment_methods", Map.of("enabled", true));

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println(paymentIntent.toJson() );
        return paymentIntent.getClientSecret();
    }

}
