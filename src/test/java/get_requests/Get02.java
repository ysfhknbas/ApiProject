package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get02 {

    @Test

    public void get02() {

        String url = "https://restful-booker.herokuapp.com/booking/0";

        Response rp = given().when().get(url);
        rp.prettyPrint();

        rp.
                then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        assertEquals("Cowboy",rp.header("Server"));

    }
}
