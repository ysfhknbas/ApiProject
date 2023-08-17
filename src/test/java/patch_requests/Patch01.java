package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
   Given
       1) https://jsonplaceholder.typicode.com/todos/198
       2) {
            "title": "Read the books"
          }
   When
        I send PATCH Request to the Url
   Then
         Status code is 200
         And response body is like   {
                                   "userId": 10,
                                   "title": "Read the books",
                                   "completed": true,
                                   "id": 198
                                  }
*/
    @Test

    public void patch01(){

        spec.pathParams("first","todos","second",198);

        Map<String,Object> expctd = new JsonPlaceHolderTestData().expectedDataMapMethod(null,"Read the books",null);
        System.out.println(expctd);

        Response rp = given(spec).body(expctd).patch("{first}/{second}");
        rp.prettyPrint();

        Map<String,Object> actualdt = rp.as(HashMap.class);
        System.out.println(actualdt);

        assertEquals(200,rp.statusCode());
        assertEquals(expctd.get("title"),actualdt.get("title"));
    }
}
