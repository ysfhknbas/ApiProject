package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public Map<String,String> bookingdatesMapMthd(String checkin, String checkout){
        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin",checkin);
        bookingdatesMap.put("checkout",checkout);

        return bookingdatesMap;
    }

    public Map<String,Object> expctdMthd(String firstname, String lastname , int totalprice, Boolean depositpaid, Map<String,String> bookingdatesMap,String additionalneeds ){
        Map<String,Object> expctd = new HashMap<>();
        expctd.put("firstname",firstname);
        expctd.put("lastname",lastname);
        expctd.put("totalprice",totalprice);
        expctd.put("depositpaid",depositpaid);
        expctd.put("bookingdates",bookingdatesMap);

        if(additionalneeds!=null)
        expctd.put("additionalneeds",additionalneeds);

        return expctd;
    }

}
