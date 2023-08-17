package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
           1) https://jsonplaceholder.typicode.com/todos/198
           2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
         I send PUT Request to the Url
       Then
             Status code is 200
             And response body is like   {
                               "userId": 21,
                               "title": "Wash the dishes",
                               "completed": false
                              }
     */

    @Test

    public void put01(){

        spec.pathParams("first","todos","second",198);

        Map<String,Object> expDt = new JsonPlaceHolderTestData().expectedDataMapMethod(21,"Wash the dishes",false);

        Response rp = given(spec).body(expDt).put("/{first}/{second}");
        rp.prettyPrint();

        Map<String,Object> resp = rp.as(HashMap.class);
        System.out.println(resp);

        assertEquals(200,rp.statusCode());
        assertEquals(expDt.get("userId"),resp.get("userId"));
        assertEquals(expDt.get("title"),resp.get("title"));
        assertEquals(expDt.get("completed"),resp.get("completed"));
    }
}
