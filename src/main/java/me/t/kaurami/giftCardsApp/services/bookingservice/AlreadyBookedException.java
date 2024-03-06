package me.t.kaurami.giftCardsApp.services.bookingservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyBookedException extends Exception{

    public AlreadyBookedException() {
        super("Gift already booked");
    }

    public AlreadyBookedException(String message) {
        super(message);
    }
}
