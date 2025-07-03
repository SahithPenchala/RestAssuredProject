package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class loggingDemo {

    @Test
    void log()
    {
        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()

                // .log().all()      //it will print all info in the console
                //.log().headers()   //it will print all headers
                //.log().body()  //it will print only response
                  .log().cookies();  //it will print only cookies

    }
}
