package ro.train_ticketing_siemens.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ro.train_ticketing_siemens.domain.Booking;
import ro.train_ticketing_siemens.service.EmailService;

@Service
@RequiredArgsConstructor
public class GmailEmailService implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendBookingConfirmation(Booking booking) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(booking.getCustomerEmail());
        message.setSubject("Confirmare bilet tren");
        message.setText("""
                Bună ziua, %s,

                Dorim să vă anunțăm că biletul dumneavoastră a fost cumpărat cu succes.

                Referință: %s
                Tren: %s
                Stația de plecare: %s
                Stația de sosire: %s
                Număr de bilete: %d
                Preț total: %s

                Vă mulțumim pentru încrederea acordată!
                """.formatted(
                booking.getCustomerName(),
                booking.getBookingReference(),
                booking.getSchedule().getTrain().getTrainNumber(),
                booking.getOriginStation().getName(),
                booking.getDestinationStation().getName(),
                booking.getNumTickets(),
                booking.getTotalPrice()
        ));

        mailSender.send(message);
    }

    @Override
    public void sendDelayNotification(Booking booking, int delayMinutes) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(booking.getCustomerEmail());
        message.setSubject("Întârzierea trenului dumneavoastră");
        message.setText("""
                Bună ziua %s,

                Dorim să vă anunțăm că trenul dumneavoastră %s va avea o întârziere de %d minute.

                Referința: %s

                Ne pare rău pentru disconfortul creat.
                """.formatted(
                booking.getCustomerName(),
                booking.getSchedule().getTrain().getTrainNumber(),
                delayMinutes,
                booking.getBookingReference()
        ));

        mailSender.send(message);
    }
}
