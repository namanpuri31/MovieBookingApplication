package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentGatewayService {
    private final RazorpayClient razorpayClient;

    @Autowired
    public PaymentGatewayService() throws RazorpayException {
        this.razorpayClient = new RazorpayClient("YOUR_API_KEY", "YOUR_SECRET_KEY");
    }

    public String createPaymentOrder(double amount) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", (int)(amount * 100)); // amount in paise
        options.put("currency", "INR");
        options.put("receipt", UUID.randomUUID().toString());

        Order order = razorpayClient.orders.create(options);
        return order.get("id");
    }
}
