package com.MovieBookingSystem.MovieBookingSystem.Mapper;
import com.MovieBookingSystem.MovieBookingSystem.Consumers.BookingConsumer;
import com.MovieBookingSystem.MovieBookingSystem.Producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;

import static com.MovieBookingSystem.MovieBookingSystem.Constants.AllConstants.BOOKING_CONSTANT;

public class Mapping {
    @Autowired
    RabbitMQProducer rabbitMQProducer;
    public <T> void routeToService(T bookingMessage,String messageType){
        if(messageType.equals(BOOKING_CONSTANT)){
            rabbitMQProducer.receiveBooking(bookingMessage);
        }
    }
}
