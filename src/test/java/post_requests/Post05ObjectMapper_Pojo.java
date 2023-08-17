package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post05ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {

    @Test

    public void post05() throws IOException {

        spec.pathParam("first","todos");

        JsonPlaceHolderPojo expctd = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        Response rp = given(spec).body(expctd).post("{first}");

        JsonPlaceHolderPojo actual= new ObjectMapper().readValue(rp.asString(), JsonPlaceHolderPojo.class);

        assertEquals(201,rp.statusCode());
        assertEquals(expctd.getCompleted(),actual.getCompleted());
        assertEquals(expctd.getTitle(),actual.getTitle());
        assertEquals(expctd.getUserId(),actual.getUserId());





    }
}
