package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static herokuapp_smoketest.S1_post.bookingid;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class S4_Patch extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/{id}
    And
          {
            "firstname": "John",
            "lastname": "Doe"
          }
    When
        Send patch request

    Then
        Status code should be 200

    And
         {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-01-01",
                "checkout": "2024-01-01"
                            },
            "additionalneeds": "Breakfast"
         }

     */

    @Test

    public void patch(){

        spec.pathParams("first","booking","second",bookingid);

        Map<String,String> expected = new HashMap<>();
        expected.put("firstname","John");
        expected.put("lastname","Doe");

        Response rp = given(spec).body(expected).patch("{first}/{second}");

        assertEquals(200,rp.statusCode());
        assertEquals(expected.get("firstname"),rp.jsonPath().getString("firstname"));
        assertEquals(expected.get("lastname"),rp.jsonPath().getString("lastname"));


    }



}
