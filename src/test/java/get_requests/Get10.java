package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/107
    When
        I send GET Request to the url
    Then
        Response body should be like that;
        {
            "firstname": "Jane",
            "lastname": "Doe",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Extra pillows please"
        }
    */

    @Test

    public void get10(){

        spec.pathParams("first","booking","second",98);

        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        Map<String,Object> expctd = new HashMap<>();
        expctd.put("firstname","Jane");
        expctd.put("lastname","Doe");
        expctd.put("totalprice",111);
        expctd.put("depositpaid",true);
        expctd.put("bookingdates",bookingdatesMap);
        expctd.put("additionalneeds","Extra pillows please");


        Response rp = given(spec).get("{first}/{second}");

        Map<String, Object> actual = rp.as(HashMap.class);
        assertEquals(200,rp.statusCode());
        assertEquals(expctd.get("firstname"),actual.get("firstname"));
        assertEquals(expctd.get("lastname"),actual.get("lastname"));
        assertEquals(expctd.get("totalprice"),actual.get("totalprice"));

        assertEquals(((Map) expctd.get("bookingdates")).get("checkin"), ((Map) expctd.get("bookingdates")).get("checkin"));
        assertEquals(((Map) expctd.get("bookingdates")).get("checkin"), ((Map) expctd.get("bookingdates")).get("checkin"));

        assertEquals(expctd.get("additionalneeds"),actual.get("additionalneeds"));
    }

    @Test

    public void get10a(){

        spec.pathParams("first","booking","second",98);

        Map<String,String> bookingdatesMap = new HerOkuAppTestData().bookingdatesMapMthd("2018-01-01","2019-01-01");

        Map<String,Object> expctd = new HerOkuAppTestData().expctdMthd("Jane","Doe",111,true,bookingdatesMap,"Extra pillows please");
        System.out.println(expctd);


        Response rp = given(spec).get("{first}/{second}");

        Map<String, Object> actual = rp.as(HashMap.class);
        assertEquals(200,rp.statusCode());
        assertEquals(expctd.get("firstname"),actual.get("firstname"));
        assertEquals(expctd.get("lastname"),actual.get("lastname"));
        assertEquals(expctd.get("totalprice"),actual.get("totalprice"));

        assertEquals(bookingdatesMap.get("checkin"), ((Map) actual.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map) actual.get("bookingdates")).get("checkin"));

        assertEquals(expctd.get("additionalneeds"),actual.get("additionalneeds"));
    }




}
