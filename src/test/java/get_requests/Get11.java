package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get11 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/29
    When
        I send GET Request to the url
    Then
        Response body should be like that;
        {
            "firstname": "Jane",
            "lastname": "Doe",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Extra pillows please"
        }
    */

    @Test

    public void get11(){

        spec.pathParams("first","booking","second",32);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expctdt = new BookingPojo("Jane","Doe",111,true,bookingDates,"Extra pillows please");
        System.out.println(expctdt);

        Response rp = given(spec).get("{first}/{second}");
        rp.prettyPrint();

        BookingPojo actual = rp.as(BookingPojo.class);
        System.out.println(actual);

        assertEquals(200,rp.statusCode());
        assertEquals(expctdt.getFirstname(),actual.getFirstname());
        assertEquals(expctdt.getLastname(),actual.getLastname());
        assertEquals(expctdt.getTotalprice(),actual.getTotalprice());
        assertEquals(expctdt.getDepositpaid(),actual.getDepositpaid());
        assertEquals(expctdt.getBookingdates().getCheckin(),actual.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actual.getBookingdates().getCheckout());
        assertEquals(expctdt.getAdditionalneeds(),actual.getAdditionalneeds());
    }
}
