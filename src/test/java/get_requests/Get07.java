package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Get07 extends PetStoreBaseUrl {

    /*
    Given
        https://petstore.swagger.io/v2/pet/123789
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
            {
            "id": 123789,
            "category": {
                "id": 0,
                "name": "Bird"
            },
            "name": "Elli",
            "photoUrls": [
                "string"
            ],
            "tags": [
                {
                    "id": 0,
                    "name": "string"
                }
            ],
            "status": "available"
            },
     */

    @Test

    public void get07(){

        spec.pathParams("first","pet","second",123789);


        Response rs = given(spec).when().get("/{first}/{second}");
        rs.prettyPrint();

        rs.then().statusCode(200).
                contentType(ContentType.JSON).
                body("category.name",equalTo("Bird"),"name",equalTo("Elli"),"status",equalTo("available"));

    }



}
