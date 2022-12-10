package utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @Data
    @AllArgsConstructor
    public class BookingParams {
      String checkIn;
      String checkout;
      String firstname;
      String lastname;
      Integer totalPrice;
      Boolean depositPaid;
      String addNotes;
    }

    @DataProvider(name = "BookingData")
    public Object[][] getBookingData(){
        return new Object[][] {
                {new BookingParams("2018-01-01", "2019-01-01", "Jim", "Brown",
                        111, true, "Breakfast")}
        };
    }

    @DataProvider(name = "BookingDataInvalid")
    public Object[][] getBookingDataInvalid(){
        return new Object[][] {

                {new BookingParams("2018-01-01", "2019-01-01", "Jim", null,
                        111, true, "Breakfast")}
        };
    }

}
