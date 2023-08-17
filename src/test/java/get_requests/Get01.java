package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

    @Test

    public void testing(){

        //set url
        String url ="https://restful-booker.herokuapp.com/bookin/10";

        //


        //send request get response
        Response rspns = given().when().get(url);
        rspns.prettyPrint();

        //assertion (a few alternatives)

        rspns.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");






    }

}
