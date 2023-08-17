package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.ObjectMapperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class S1_post extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
                     },
    "additionalneeds" : "Breakfast"
    }

    When
        send post request
    Then
        Status code should be 200
        Response body should be:
            {
    "bookingid": 15268,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
                        },
        "additionalneeds": "Breakfast"
                }
             }
     */

    public static  int bookingid;
    @Test

    public void s1_post(){

        spec.pathParam("first","booking");

        BookingDatesPojo bookingdates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expected = new BookingPojo("Jim","Brown",111,true,bookingdates,"Breakfast");

        Response rp = given(spec).body(expected).post("{first}");
        BookingResponsePojo actual = ObjectMapperUtils.convertJsonToJavaObject(rp.asString(),BookingResponsePojo.class);
        System.out.println(actual);
        bookingid = actual.getBookingid();
        System.out.println(bookingid);

        assertEquals(200,rp.statusCode());
        assertEquals(expected.getFirstname(),actual.getBooking().getFirstname());
        assertEquals(expected.getLastname(),actual.getBooking().getLastname());
        assertEquals(expected.getTotalprice(),actual.getBooking().getTotalprice());
        assertEquals(expected.getDepositpaid(),actual.getBooking().getDepositpaid());
        assertEquals(bookingdates.getCheckin(),actual.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(),actual.getBooking().getBookingdates().getCheckout());
        assertEquals(expected.getAdditionalneeds(),actual.getBooking().getAdditionalneeds());
    }
}
