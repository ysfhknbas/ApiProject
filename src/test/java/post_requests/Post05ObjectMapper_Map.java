package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post05ObjectMapper_Map extends JsonPlaceHolderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) { "userId": 55,
               "title": "Tidy your room",
               "completed": false
               }


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

    public void Post05() throws IOException {

        spec.pathParam("first","todos");

        Map<String,Object> exptdt = new JsonPlaceHolderTestData().expectedDataMapMethod(55,"Tidy your room",false);
        System.out.println(exptdt);

        Response rp = given(spec).body(exptdt).post("{first}");

        Map<String,Object> actual = new ObjectMapper().readValue(rp.asString(), HashMap.class);
        System.out.println(actual);

        assertEquals(201,rp.statusCode());
        assertEquals(exptdt.get("completed"),actual.get("completed"));
        assertEquals(exptdt.get("title"),actual.get("title"));
        assertEquals(exptdt.get("userId"),actual.get("userId"));





    }

}
