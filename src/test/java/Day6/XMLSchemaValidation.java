package Day6;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class XMLSchemaValidation {

    @Test
    void xmlSchemaValidation()
    {
       given()

               .when()
               .get("give api URl here")
               .then()
               .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("Spcify the name of the schema file"));
    }
}
