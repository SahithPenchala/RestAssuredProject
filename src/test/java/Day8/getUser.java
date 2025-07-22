package Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class getUser {

    @Test
    void test_getUser(ITestContext context)
    {
//        int id = (int) context.getAttribute("user_id");    // this comes from craete user class
        int id = (int) context.getSuite().getAttribute("user_id");
        String bearerToken = "e2baf056dc81a46c680a02fb95d1ec40d96cb4967ea83962f3bff07341c50f49";
        given()
                   .headers("Authorization", "Bearer " + bearerToken)
                   .pathParam("id", id)

                .when()
                   .get("https://gorest.co.in/public/v2/users/{id}")

                .then()
                   .statusCode(200)
                   .log().all();


    }
}