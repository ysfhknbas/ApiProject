package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
        Given
              https://jsonplaceholder.typicode.com/todos
      When
          I send GET Request to the URL
      Then
          1)Status code is 200
          2)Print all ids greater than 190 on the console
            Assert that there are 10 ids greater than 190
          3)Print all userIds whose ids are less than 5 on the console
            Assert that the number of userIds whose ids are less than 5 is 4
          4)Print all titles whose ids are less than 5
            Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test

    public void get08(){

        spec.pathParams("first","todos");

        Response rs = given().spec(spec).when().get("/{first}");
       rs.prettyPrint();

       assertEquals(200,rs.statusCode());

       JsonPath json= rs.jsonPath();
       List<Integer> ids = json.getList("id");
       System.out.println(ids);
        System.out.println(ids.size());
       int i=0;
        for (int w: ids)
        {
        if (w>190) i++;
        }
        System.out.println(i);

      List<Object>listGroovy = json.getList("findAll{it.id>190}.id");
        System.out.println(listGroovy);

        assertEquals(10,listGroovy.size());

        List<Integer>listGroovy2 = json.getList("findAll{it.id<5}.userId");
        System.out.println(listGroovy2);

        assertEquals(4,listGroovy2.size());

        List<String>listGroovy3 = json.getList("findAll{it.id<5}.title");
        System.out.println(listGroovy3);

        assertTrue(listGroovy3.contains("delectus aut autem"));










    }


}
