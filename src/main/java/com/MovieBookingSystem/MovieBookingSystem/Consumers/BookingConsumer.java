package com.MovieBookingSystem.MovieBookingSystem.Consumers;

import com.MovieBookingSystem.MovieBookingSystem.Configuration.RabbitMQConfig;
import com.MovieBookingSystem.MovieBookingSystem.Service.SeatBookingService;
import com.MovieBookingSystem.MovieBookingSystem.Util.SeatAvailabilityDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BookingConsumer {
    @Autowired
    SeatBookingService seatBookingService;
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveBooking(SeatAvailabilityDTO request) {
        System.out.println("✅ Received BookingRequest: " + request);
        seatBookingService.bookSeat(request.getShowId(),request.getSeatId());
    }
}
