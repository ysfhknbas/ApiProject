package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AutenticationGmiBank.gentoken;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp(){

        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).
                addHeader("Authorization","Bearer "+gentoken()).setBaseUri("https://www.gmibank.com").build();

    }
}
