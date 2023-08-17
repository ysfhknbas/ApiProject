package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

       public static String genToken(){

        Map<String,String> tokenBody = new HashMap<>();
        tokenBody.put("username","admin");
        tokenBody.put("password","password123");

        Response rp = given().contentType(ContentType.JSON).body(tokenBody).post("https://restful-booker.herokuapp.com/auth");
        return rp.jsonPath().getString("token");
    }
}
