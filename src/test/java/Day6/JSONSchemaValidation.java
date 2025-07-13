package Day6;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

//https://jsonformatter.org/json-to-jsonschema - this website used to convert json to json schema

public class JSONSchemaValidation
{
    // below is json schema validation
    @Test
    void jsonSchemaValidation()
    {
        given()
                .when()
                .get("http://localhost:3000/store")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONStoreSchema.json"));  //this will check for schema file under resouce and will test

    }

    //
     //convert xml to xsd
}
