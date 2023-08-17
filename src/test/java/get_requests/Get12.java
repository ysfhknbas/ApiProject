package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class Get12 extends GorestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        "Gov. Vrinda Panicker", "Sen. Devika Embranthiri" and "Rev. Jay Shukla" are among the users
    And
        The female users are less than or equals to male users
*/

    @Test

    public void get12(){

        spec.pathParam("first","users");

        Response rp= given(spec).get("{first}");
        rp.prettyPrint();

        rp.then().body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),"data",hasSize(10),
                "data.status",hasItem("active"), "data.name",hasItems("Shreyashi Dutta CPA","Deepali Devar","Chandra Gill II"));

        List<String> gender = rp.jsonPath().getList("data.gender");
        System.out.println(gender);

        long femalecnt = gender.stream().filter(t-> Objects.equals(t, "female")).count();
        System.out.println(femalecnt);

        List<String> femaleList = rp.jsonPath().getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList = rp.jsonPath().getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size()<=maleList.size());






    }

}
