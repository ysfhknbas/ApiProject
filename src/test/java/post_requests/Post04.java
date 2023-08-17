package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post04 extends HerOkuAppBaseUrl {

    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "John",
                "lastname": "Doe",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
                }
        When
          I send POST Request to the URL
       Then
          Status code is 200
      And
          Response body is like {
                                  "bookingid": 16,
                                  "booking" :{
                                        "firstname": "John",
                                        "lastname": "Doe",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        },
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test

    public void post04(){

        //set url
        spec.pathParams("first","booking");

        //set expected data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo exptdt = new BookingPojo("John","Doe",999,true,bookingdates,"Breakfast");
        System.out.println(exptdt);


        //send request get response

        Response rp = given(spec).body(exptdt).post("{first}");
        rp.prettyPrint();

        BookingResponsePojo actual = rp.as(BookingResponsePojo.class);
        System.out.println(actual);

        assertEquals(200,rp.statusCode());
        assertEquals(exptdt.getFirstname(),actual.getBooking().getFirstname());
        assertEquals(exptdt.getLastname(),actual.getBooking().getLastname());
        assertEquals(exptdt.getTotalprice(),actual.getBooking().getTotalprice());
        assertEquals(exptdt.getDepositpaid(),actual.getBooking().getDepositpaid());
        assertEquals(bookingdates.getCheckin(),actual.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(),actual.getBooking().getBookingdates().getCheckout());
        assertEquals(exptdt.getAdditionalneeds(),actual.getBooking().getAdditionalneeds());



    }

}
