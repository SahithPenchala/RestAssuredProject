package Day8;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class createUser {

    @Test
    void test_CreateUser(ITestContext context)  //by using thi itextxontext we will
    {
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        String bearerToken = "e2baf056dc81a46c680a02fb95d1ec40d96cb4967ea83962f3bff07341c50f49";
        int id = given()    // store id value here
                    .headers("Authorization","Bearer "+bearerToken) // here we are passing autorizton token
                    .contentType("application/json")     //
                    .body(data.toString())   // we are telling data in body to be in string

                .when()
                   .post("https://gorest.co.in/public/v2/users")
                   .jsonPath().getInt("id");   // it will capture only id from the response and check 25 line
                   System.out.println("generated id is="+id);
//                   context.setAttribute("user_id",id);  //here user id will be used in other test classes
                  //this context will only available in one test flow(test level not suite level
                  context.getSuite().setAttribute("user_id",id);  // now this is available at the suite level


    }

}
