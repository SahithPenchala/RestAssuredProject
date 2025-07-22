package Day8;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class updateUser {
    private static final Logger log = LoggerFactory.getLogger(updateUser.class);

    @Test
    void test_updateUser(ITestContext context)
    {
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","FeMale");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

        String bearerToken = "e2baf056dc81a46c680a02fb95d1ec40d96cb4967ea83962f3bff07341c50f49";
//        int id = (int) context.getAttribute("user_id");
        int id = (int) context.getSuite().getAttribute("user_id");
        given()    // store id value here
                .headers("Authorization","Bearer "+bearerToken) // here we are passing autorizton token
                  .contentType("application/json")
                  .pathParam("id",id)
                  .body(data.toString())   // we are telling data in body to be in string

                .when()
                  .put("https://gorest.co.in/public/v2/users/{id}")

                .then()
                  .statusCode(200)
                  .log().all();
    }
 }

