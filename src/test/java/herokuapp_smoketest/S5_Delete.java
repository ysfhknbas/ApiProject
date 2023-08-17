package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_post.bookingid;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class S5_Delete extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/377

    When
        Send delete request

    Then
        Status code should be 201

    And
        Body should be "created"
     */

    @Test

    public void delete(){

        spec.pathParams("first","booking","second",bookingid);

        String expected = "Created";

        Response rp = given(spec).delete("{first}/{second}");
        rp.prettyPrint();

        assertEquals(201,rp.statusCode());
        assertEquals(expected,rp.asString());

    }

}
