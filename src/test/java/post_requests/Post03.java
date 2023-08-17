package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post03 extends JsonPlaceHolderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
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

    public void post03(){

        spec.pathParam("first","todos");

        JsonPlaceHolderPojo expctdt = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(expctdt);

        Response rp = given(spec).body(expctdt).post("{first}");
        rp.prettyPrint();

        JsonPlaceHolderPojo actual = rp.as(JsonPlaceHolderPojo.class);
        System.out.println(actual);

        assertEquals(201,rp.statusCode());
        assertEquals(expctdt.getUserId(),actual.getUserId());
        assertEquals(expctdt.getTitle(),actual.getTitle());
        assertEquals(expctdt.getCompleted(),actual.getCompleted());

    }
}
