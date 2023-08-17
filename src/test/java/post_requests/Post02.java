package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {

    /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
             "firstname": "John",
             "lastname": "Doe",
             "totalprice": 11111,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2021-09-09",
                 "checkout": "2021-09-21"
              }
           }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 5315,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 11111,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2021-09-09",
                                                    "checkout": "2021-09-21"
                                                }
                                            }
                                         }
 */

    @Test

    public void post02(){

        spec.pathParam("first","booking");

        Map<String,String> bookingdates = new HerOkuAppTestData().bookingdatesMapMthd("2021-09-09","2021-09-21");
        Map<String,Object> exptdt = new HerOkuAppTestData().expctdMthd("John","Doe",11111,true,bookingdates,null);
        System.out.println(exptdt);

        Response rp = given(spec).body(exptdt).post("{first}");
        rp.prettyPrint();

        Map<String,Object> actual = rp.as(HashMap.class);

        assertEquals(200,rp.statusCode());
        assertEquals(exptdt.get("firstname"),((Map)actual.get("booking")).get("firstname"));
        assertEquals(exptdt.get("lastname"),((Map)actual.get("booking")).get("lastname"));
        assertEquals(exptdt.get("totalprice"),((Map)actual.get("booking")).get("totalprice"));
        assertEquals(exptdt.get("depositpaid"),((Map)actual.get("booking")).get("depositpaid"));
        assertEquals(bookingdates.get("checkin"),((Map)((Map)actual.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdates.get("checkout"),((Map)((Map)actual.get("booking")).get("bookingdates")).get("checkout"));

    }

}
