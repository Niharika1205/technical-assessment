package technical.assessment.requestBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import utils.TestDataProvider;

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
        private String checkIn;
        private String checkout;
    }

    /**
     * Method to create request body for Create booking request
     * @param bookParam
     * @return booking request payload
     */
    public CreateBooking createBookingRequest(TestDataProvider.BookingParams bookParam){
        BookingDates bookingDates = BookingDates.builder().checkIn(bookParam.getCheckIn()).checkout(bookParam.getCheckout()).build();
        CreateBooking bookingRequest = CreateBooking.builder().firstname(bookParam.getFirstname()).lastname(bookParam.getLastname())
                .totalprice(bookParam.getTotalPrice()).depositpaid(bookParam.getDepositPaid()).bookingdates(bookingDates)
                .additionalneeds(bookParam.getAddNotes()).build();

        return bookingRequest;
    }

    /**
     * Method to create invalid request body for Create booking request
     * @param bookingParams
     * @return invalid booking request payload
     */
    public CreateBookingInvalid createBookingRequestInvalid(TestDataProvider.BookingParams bookingParams){
        BookingDatesInvalid bookingDates = BookingDatesInvalid.builder().checkIn(bookingParams.getCheckIn())
                .checkout(bookingParams.getCheckout()).build();
        CreateBookingInvalid bookingRequest = CreateBookingInvalid.builder().firstname(bookingParams.getFirstname())
                .lastname(bookingParams.getLastname()).totalprice(bookingParams.getTotalPrice()).depositpaid(bookingParams
                        .getDepositPaid()).bookingdates(bookingDates).additionalneeds(bookingParams.getAddNotes()).build();

        return bookingRequest;
    }

}
