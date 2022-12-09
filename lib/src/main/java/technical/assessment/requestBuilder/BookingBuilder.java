package technical.assessment.requestBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

public class BookingBuilder {

    @Data
    @Builder
    public static class CreateBooking{
        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        private BookingDates bookingdates;
        private String additionalneeds;
    }

    @Data
    @Builder
    public static class CreateBookingInvalid{
        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        private BookingDatesInvalid bookingdates;
        private String additionalneeds;
    }
    @Data
    @Builder
    public static class BookingDates {
        @JsonProperty("checkin")
        private String checkIn;
        private String checkout;
    }

    @Data
    @Builder
    public static class BookingDatesInvalid {
        private int checkIn;
        private String checkout;
    }

    public CreateBooking createBookingRequest(String checkIn, String checkout, String firstName, String lastName,
                                              int totalPrice, boolean depositPaid, String addNeeds){
        BookingDates bookingDates = BookingDates.builder().checkIn(checkIn).checkout(checkout).build();
        CreateBooking bookingRequest = CreateBooking.builder().firstname(firstName).lastname(lastName)
                .totalprice(totalPrice).depositpaid(depositPaid).bookingdates(bookingDates)
                .additionalneeds(addNeeds).build();

        return bookingRequest;
    }

    public CreateBookingInvalid createBookingRequestInvalid(int checkIn, String checkout, String firstName, String lastName,
                                              int totalPrice, boolean depositPaid, String addNeeds){
        BookingDatesInvalid bookingDates = BookingDatesInvalid.builder().checkIn(checkIn).checkout(checkout).build();
        CreateBookingInvalid bookingRequest = CreateBookingInvalid.builder().firstname(firstName).lastname(lastName)
                .totalprice(totalPrice).depositpaid(depositPaid).bookingdates(bookingDates)
                .additionalneeds(addNeeds).build();

        return bookingRequest;
    }

}
