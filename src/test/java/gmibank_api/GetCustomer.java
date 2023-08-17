package gmibank_api;

import base_urls.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPojo;
import pojos.CustomerPojo;
import pojos.UserPojo;
import utils.ObjectMapperUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static utils.AutenticationGmiBank.gentoken;

public class GetCustomer extends GmiBankBaseUrl {

        /*
        Given
          https://www.gmibank.com/api/tp-customers/110452
        When
         I send GET Request to the URL
        Then
         Status code is 200
         And response body is like {
                                        "id": 110452,
                                        "firstName": "Jasmine",
                                        "lastName": "Stehr",
                                        "middleInitial": "V",
                                        "email": "marni.zboncak@yahoo.com",
                                        "mobilePhoneNumber": "463-609-2097",
                                        "phoneNumber": "1-112-497-0270",
                                        "zipCode": "16525",
                                        "address": "14387 Al Ridge5343 Bert Burgs",
                                        "city": "Waltermouth",
                                        "ssn": "761-59-2911",
                                        "createDate": "2021-11-28T21:00:00Z",
                                        "zelleEnrolled": false,
                                        "country": {
                                            "id": 3,
                                            "name": "USA",
                                            "states": null
                                                    },
                                        "state": "California",
                                        "user": {
                                            "id": 110016,
                                            "login": "leopoldo.reinger",
                                            "firstName": "Jasmine",
                                            "lastName": "Stehr",
                                            "email": "marni.zboncak@yahoo.com",
                                            "activated": true,
                                            "langKey": "en",
                                            "imageUrl": null,
                                            "resetDate": null
                                                },
                                        "accounts": []
                                    }
      */

    @Test

    public void getCustomerTest(){

        spec.pathParams("first","api","second","tp-customers","third",110452);

        CountryPojo country = new CountryPojo(3,"USA",null);

        UserPojo user = new UserPojo(110016,"leopoldo.reinger","Jasmine","Stehr","marni.zboncak@yahoo.com",true,"en",null,null);
        ArrayList<Object> accounts = new ArrayList<>();

        CustomerPojo expected = new CustomerPojo(110452,"Jasmine","Stehr","V","marni.zboncak@yahoo.com","463-609-2097","1-112-497-0270",
                "16525","14387 Al Ridge5343 Bert Burgs","Waltermouth","761-59-2911","2021-11-28T21:00:00Z",false,country,"California",
                user,accounts);

        System.out.println(expected);

        Response rp = given(spec).get("{first}/{second}/{third}");
        rp.prettyPrint();

        CustomerPojo actual = ObjectMapperUtils.convertJsonToJavaObject(rp.asString(), CustomerPojo.class);
        System.out.println(actual);

        assertEquals(200,rp.statusCode());
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getFirstName(),actual.getFirstName());
        assertEquals(expected.getLastName(),actual.getLastName());
        assertEquals(expected.getMiddleInitial(),actual.getMiddleInitial());
        assertEquals(expected.getEmail(),actual.getEmail());
        assertEquals(expected.getMobilePhoneNumber(),actual.getMobilePhoneNumber());
        assertEquals(expected.getPhoneNumber(),actual.getPhoneNumber());
        assertEquals(expected.getZipCode(),actual.getZipCode());
        assertEquals(expected.getAddress(),actual.getAddress());
        assertEquals(expected.getCity(),actual.getCity());
        assertEquals(expected.getSsn(),actual.getSsn());
        assertEquals(expected.getCreateDate(),actual.getCreateDate());
        assertEquals(expected.isZelleEnrolled(),actual.isZelleEnrolled());
        assertEquals(country.getId(),actual.getCountry().getId());
        assertEquals(country.getName(),actual.getCountry().getName());
        assertEquals(country.getStates(),actual.getCountry().getStates());
        assertEquals(expected.getState(),actual.getState());
        assertEquals(user.getId(),actual.getUser().getId());
        assertEquals(user.getLogin(),actual.getUser().getLogin());
        assertEquals(user.getFirstName(),actual.getUser().getFirstName());
        assertEquals(user.getLastName(),actual.getUser().getLastName());
        assertEquals(user.getEmail(),actual.getUser().getEmail());
        assertEquals(user.isActivated(),actual.getUser().isActivated());
        assertEquals(user.getLangKey(),actual.getUser().getLangKey());
        assertEquals(user.getImageUrl(),actual.getUser().getImageUrl());
        assertEquals(user.getResetDate(),actual.getUser().getResetDate());
        assertEquals(expected.getAccounts(),actual.getAccounts());

    }

}
