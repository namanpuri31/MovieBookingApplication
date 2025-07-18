package com.MovieBookingSystem.MovieBookingSystem;

import com.MovieBookingSystem.MovieBookingSystem.Service.SeatBookingService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(classes = com.MovieBookingSystem.MovieBookingSystem.MovieBookingSystemApplication.class)
public class BookingConcurrencyTest {

    @Autowired
    private SeatBookingService bookingService; // Your actual service that handles locking and booking

    @Test
    public void testConcurrentSeatBooking() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Long showId = 10L;
        Long seatId = 1L;

        for (int i = 0; i < threadCount; i++) {
            final int userNumber = i;
            executor.submit(() -> {
                try {
                    String result = bookingService.bookSeat(showId, seatId);
                    if (result=="Seat Booked") {
                        System.out.println("✅ Seat booked by user");
                    } else {
                        System.out.println("❌ Booking failed for user");
                    }
                } catch (Exception e) {
                    System.out.println("💥 Error for user-" + userNumber + ": " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Wait for all threads to complete
        executor.shutdown();
    }
}
