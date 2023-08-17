package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;

public class Get06 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/32
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
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

    public void get06(){
        spec.pathParams("first","booking","second",1706);


        Response rs = given().spec(spec).when().get("/{first}/{second}");
        rs.prettyPrint();

        rs.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Josh")).
                body("lastname",equalTo("Allen")).
                body("totalprice",equalTo(111)).
                body("depositpaid",equalTo(true)).
                body("bookingdates.checkin",equalTo("2018-01-01")).
                body("bookingdates.checkout",equalTo("2019-01-01")).
                body("additionalneeds",equalTo("super bowls"));

        JsonPath jsonPath = rs.jsonPath();
        assertEquals("Josh",jsonPath.getString("firstname"));


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("firstname"),"Josh");
        softAssert.assertAll();





    }




}
