package com.practice.service;

import com.practice.model.Booking;
import com.practice.model.PaymentStatus;
import com.practice.model.Show;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {

    private Map<Integer, Show> showMap;
    private Map<Integer, Booking> bookingMap;
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    private Map<Integer, ScheduledFuture> scheduledFutureMap;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Integer holdSeats(Show show, List<Integer> seatIds) throws Exception{
        if(!show.holdSeats(seatIds)){
            throw new Exception("Could not hold seats");
        }
        int bookingId = atomicInteger.incrementAndGet();
        Booking newBooking = new Booking(bookingId, show, seatIds);
        bookingMap.put(bookingId, newBooking);
        scheduledFutureMap.put(bookingId,scheduledExecutorService.schedule(()-> {
            try {
                expireBooking(bookingId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, 5, TimeUnit.MINUTES));
        return bookingId;
    }

    public void confirmBooking(Integer bookingId){
        Booking booking = bookingMap.get(bookingId);
        if(booking.getPaymentStatus() == PaymentStatus.CONFIRMED_PAYMENT){
            return;
        }
        booking.confirmBooking();
        ScheduledFuture scheduledFuture = scheduledFutureMap.get(bookingId);
        scheduledFuture.cancel(false);
        scheduledFutureMap.remove(bookingId);
        bookingMap.remove(bookingId);
    }

    public void expireBooking(Integer bookingId) throws Exception {
        Booking currentBooking = bookingMap.get(bookingId);
        if(currentBooking.getPaymentStatus() == PaymentStatus.CONFIRMED_PAYMENT){
            return;
        }
        Show currentShow = currentBooking.getShow();
        currentShow.releaseSeats(currentBooking.getSeatIds());
        Booking booking = bookingMap.get(bookingId);
        booking.cancelBooking();
        bookingMap.remove(bookingId);
    }


}
