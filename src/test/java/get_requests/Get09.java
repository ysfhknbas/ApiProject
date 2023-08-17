package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends JsonPlaceHolderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test

    public void get09(){

        spec.pathParams("first","todos","second",2);

        Map<String, Object> expected = new JsonPlaceHolderTestData().expectedDataMapMethod(1,"quis ut nam facilis et officia qui",false);
        expected.put("Via","1.1 vegur");
        expected.put("Server","cloudflare");

        Response rp5 = given(spec).get("{first}/{second}");
        rp5.prettyPrint();

        Map<String,Object> actualData = rp5.as(HashMap.class);
        assertEquals(200,rp5.statusCode());
        assertEquals(expected.get("userId"),actualData.get("userId"));
        assertEquals(expected.get("title"),actualData.get("title"));
        assertEquals(expected.get("completed"),actualData.get("completed"));
        assertEquals(expected.get("Via"),rp5.getHeader("Via"));
        assertEquals(expected.get("Server"),rp5.getHeader("Server"));






    }

}
