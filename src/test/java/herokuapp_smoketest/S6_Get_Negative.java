package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_post.bookingid;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class S6_Get_Negative extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/{id}

    When
        Send get request

    Then
        Status code should be 404

    And
        Body should be "Not Found"

    */

    @Test

    public void getNeg(){
        spec.pathParams("first","booking","second",bookingid);

        String expected = "Not Found";

        Response rp = given(spec).get("{first}/{second}");
        rp.prettyPrint();

        assertEquals(404,rp.statusCode());
        assertEquals(expected,rp.asString());
    }




}
