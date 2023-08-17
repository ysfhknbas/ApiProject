package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestAndResponse {
    public static void main(String[] args) {

        String url = "https://restful-booker.herokuapp.com/booking/10";
        Response rspns = given().when().get(url);
        rspns.prettyPrint();

        int code= rspns.statusCode();
        System.out.println(code);

        String type = rspns.contentType();
        System.out.println(type);

        String stats = rspns.statusLine();
        System.out.println(stats);

        System.out.println(rspns.headers());

        System.out.println(rspns.time());


    }
}
