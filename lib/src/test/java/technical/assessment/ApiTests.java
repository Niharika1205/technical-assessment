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
import utils.Constants;
import utils.TestDataProvider;

public class ApiTests {
    BookingBuilder bookingBuilderObj = new BookingBuilder();
    Header contentType = new Header("Content-Type", "application/json");
    Header accept = new Header("Accept", "application/json");
    Headers headers = new Headers(contentType, accept);

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = Constants.baseUrl;
    }

    @Test(dataProvider = "BookingData",
    dataProviderClass = TestDataProvider.class,
    description = "Method to validate positive flow for creating a booking. " +
            "Validate success status code and Booking ID not null.")
    public void validateBookingRequest(TestDataProvider.BookingParams bookingParams) {

        RequestSpecification request = RestAssured.given().log().all();
        BookingBuilder.CreateBooking reqBody = bookingBuilderObj.createBookingRequest(bookingParams);
        Response response = request.headers(headers).body(reqBody).post();
        response.then().assertThat().statusCode(200);
        Assert.assertNotNull(response.getBody().jsonPath().getString("bookingid"),
                "Booking ID not generated");

    }

    @Test(dataProvider = "BookingData",
            dataProviderClass = TestDataProvider.class,
            description = "Method to validate internal server error if payload field format is incorrect.")
    public void validateInvalidFieldError(TestDataProvider.BookingParams bookingParams) {
        RequestSpecification request = RestAssured.given().log().all();
        BookingBuilder.CreateBookingInvalid reqBody = bookingBuilderObj.createBookingRequestInvalid(bookingParams);
        Response response = request.headers(headers).body(reqBody).post();
        response.then().assertThat().statusCode(500);
    }

    @Test(dataProvider = "BookingDataInvalid",
            dataProviderClass = TestDataProvider.class,
            description = "Method to validate internal server error if null value is passed for String lastname.")
    public void validateNullStringError(TestDataProvider.BookingParams bookingParams) {

        RequestSpecification request = RestAssured.given().log().all();
        BookingBuilder.CreateBooking reqBody = bookingBuilderObj.createBookingRequest(bookingParams);
        Response response = request.headers(headers).body(reqBody).post();
        response.then().assertThat().statusCode(500);

    }
}
