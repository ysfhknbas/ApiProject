package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static herokuapp_smoketest.S1_post.bookingid;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class S3_Get extends HerOkuAppBaseUrl {

/*
    Given
        https://restful-booker.herokuapp.com/booking/{id}
    When
        Send the get request
    Then
        Status Code should be 200
    And
        Response body should be
        {
                "firstname": "Mark",
                "lastname": "Twain",
                "totalprice": 555,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2023-01-01",
                    "checkout": "2024-01-01"
                    },
                "additionalneeds": "Extra pillow"
            }

     */

    @Test

    public void s3get(){

        spec.pathParams("first","booking","second",bookingid);

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2023-01-01","2024-01-01");
        BookingPojo expected = new BookingPojo("Mark","Twain",555,false,bookingDatesPojo,"Extra pillow");

        Response rp = given(spec).get("{first}/{second}");
        rp.prettyPrint();

        BookingPojo actual = ObjectMapperUtils.convertJsonToJavaObject(rp.asString(),BookingPojo.class);

        assertEquals(200,rp.statusCode());
        assertEquals(expected.getFirstname(),actual.getFirstname());
        assertEquals(expected.getLastname(),actual.getLastname());
        assertEquals(expected.getTotalprice(),actual.getTotalprice());
        assertEquals(expected.getDepositpaid(),actual.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actual.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actual.getBookingdates().getCheckout());
        assertEquals(expected.getAdditionalneeds(),actual.getAdditionalneeds());







    }

}
