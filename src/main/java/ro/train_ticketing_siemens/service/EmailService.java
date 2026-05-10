package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.domain.Booking;

public interface EmailService {

    void sendBookingConfirmation(Booking booking);

    void sendDelayNotification(Booking booking, int delayMinutes);
}
