package technical.assessment;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import technical.assessment.requestBuilder.BookingBuilder;

public class ApiTests {
    BookingBuilder bookingBuilderObj = new BookingBuilder();
    Header contentType = new Header("Content-Type", "application/json");
    Header accept = new Header("Accept", "application/json");
    Headers headers = new Headers(contentType, accept);

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
    }

    /**
     * Method to validate positive flow for creating a booking.
     * Validate success status code and Booking ID not null.
     */
    @Test
    public void validateBookingRequest() {

        RequestSpecification request = RestAssured.given().log().all();
        BookingBuilder.CreateBooking reqBody = bookingBuilderObj.createBookingRequest
                ("2018-01-01", "2019-01-01", "Jim", "Brown", 111,
                        true, "Breakfast");

        Response response = request.headers(headers).body(reqBody).post();
        response.then().assertThat().statusCode(200);
        Assert.assertNotNull(response.getBody().jsonPath().getString("bookingid"),
                "Booking ID not generated");

    }

    /**
     * Method to validate 500 status code if payload field format is incorrect.
     */
    @Test(description = "wrongly spelled checkin field name to get error")
    public void validateInvalidFieldError() {
        RequestSpecification request = RestAssured.given().log().all();
        BookingBuilder.CreateBookingInvalid reqBody = bookingBuilderObj.createBookingRequestInvalid
                (0, "", "", "", 111, true, "");

        Response response = request.headers(headers).body(reqBody).post();
        response.then().assertThat().statusCode(500);
    }

    /**
     * Method to validate 500 error code if null value is passed for String lastname.
     */
    @Test
    public void validateNullStringError() {

        RequestSpecification request = RestAssured.given().log().all();
        BookingBuilder.CreateBooking reqBody = bookingBuilderObj.createBookingRequest
                ("2018-01-01", "2019-01-01", "Jim", null, 111,
                        true, "Breakfast");

        Response response = request.headers(headers).body(reqBody).post();
        response.then().assertThat().statusCode(500);

    }
}
