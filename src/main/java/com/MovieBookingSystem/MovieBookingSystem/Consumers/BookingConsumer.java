package com.MovieBookingSystem.MovieBookingSystem.Consumers;

import com.MovieBookingSystem.MovieBookingSystem.Configuration.RabbitMQConfig;
import com.MovieBookingSystem.MovieBookingSystem.Service.SeatBookingService;
import com.MovieBookingSystem.MovieBookingSystem.Util.SeatAvailabilityDTO;
import com.MovieBookingSystem.MovieBookingSystem.Util.SeatBookingDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BookingConsumer {
    @Autowired
    SeatBookingService seatBookingService;
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveBooking(SeatBookingDTO request) {
        System.out.println("✅ Received BookingRequest: " + request);
        seatBookingService.reserveSeatAndPay(request.getShowId(),request.getSeatId(),request.getUserId());
    }
}
