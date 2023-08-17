package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AutenticationGmiBank {

    public static  String gentoken(){

        Map<String,Object> tokenBody = new HashMap<>();
        tokenBody.put("password","Mark.123");
        tokenBody.put("rememberMe",true);
        tokenBody.put("username","mark_twain");

        Response token = given().body(tokenBody).contentType(ContentType.JSON).post("https://gmibank.com/api/authenticate");

        return token.jsonPath().getString("id_token");



    }
}
