package get_requests;

import base_urls.HerOkuAppBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class Get05 extends HerOkuAppBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
             Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */

    @Test

    public void get05(){

        spec.pathParams("first","booking").queryParams("firstname","John","lastname","Smith");


        Response rs = given().spec(spec).when().get("/{first}");
        rs.prettyPrint();


        assertEquals(200,rs.statusCode());
        assertTrue(rs.asString().contains("bookingid"));



    }



}
