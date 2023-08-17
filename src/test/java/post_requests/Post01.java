package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.path.json.JsonPath.given;
import static org.testng.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test

    public void post01(){

        spec.pathParams("first","todos");

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        expectedData.put("id",201);

        System.out.println(expectedData);

        Response rs = given(spec).contentType(ContentType.JSON).body(expectedData).when().post("{first}");
        rs.prettyPrint();



        //assertion
        Map<String,Object> respData = rs.as(HashMap.class);

        assertEquals(201,rs.statusCode());
        assertEquals(expectedData.get("userId"),respData.get("userId"));
        assertEquals(expectedData.get("title"),respData.get("title"));
        assertEquals(expectedData.get("completed"),respData.get("completed"));

        assertEquals(expectedData,respData);
    }


    public void post01b(){

        spec.pathParams("first","todos");

        Map<String,Object> expectedData = new JsonPlaceHolderTestData().expectedDataMapMethod(55,"Tidy your room",false);

        System.out.println(expectedData);

        Response rsb = given(spec).contentType(ContentType.JSON).body(expectedData).when().post("{first}");
        rsb.prettyPrint();



        //assertion
        Map<String,Object> respData = rsb.as(HashMap.class);

        assertEquals(201,rsb.statusCode());
        assertEquals(expectedData.get("userId"),respData.get("userId"));
        assertEquals(expectedData.get("title"),respData.get("title"));
        assertEquals(expectedData.get("completed"),respData.get("completed"));

        assertEquals(expectedData,respData);

    }


}
