package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.AuthenticationHerOkuApp;
import utils.ObjectMapperUtils;

import static herokuapp_smoketest.S1_post.bookingid;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static utils.AuthenticationHerOkuApp.genToken;

public class S2_put extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/{id}
    And
    {
    "firstname" : "Mark",
    "lastname" : "Twain",
    "totalprice" : 555,
    "depositpaid" : false,
    "bookingdates" : {
        "checkin" : "2023-01-01",
        "checkout" : "2024-01-01"
    },
    "additionalneeds" : "Extra pillow"
    }
    when
        send request
    Then
        status code should be 200
    And
        Body should be:
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

     public void s2_putTest() {

          spec.pathParams("first", "booking", "second", bookingid);

          BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2023-01-01","2024-01-01");
          BookingPojo expected = new BookingPojo("Mark","Twain",555,false,bookingDatesPojo,"Extra pillow");

          Response rp = given(spec).body(expected).put("{first}/{second}");
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


