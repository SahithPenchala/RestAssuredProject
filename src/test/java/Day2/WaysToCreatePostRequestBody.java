package Day2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;
/* Ways to create POST request body
1)Hashmap(it is a java collection,data stored in key value pair)
2)using org.json
3)using pojo class
4)external json file data
*/

public class WaysToCreatePostRequestBody
{
  //  @Test(priority = 1)
    void testPostUsingHashmap()
    {
        HashMap data = new HashMap();

         data.put("name","Sam");
         data.put("location","france");
         data.put("phone","79889931574");

         String courseArr[] = {"C","C++"};
         data.put("courses",courseArr);

         given()
                 .contentType("application/json")
                 .body(data)

                 .when()
                 .post("http://localhost:3000/students")
                 .then()
                 .statusCode(201)
                 .body("name",equalTo("Sam"))
                 .body("location",equalTo("france"))
                 .body("phone",equalTo("79889931574"))
                 .body("courses[0]",equalTo("C"))
                 .body("courses[1]",equalTo("C++"))
                 .header("contentType","application/json")
                 .log().all();
    }


   // @Test(priority = 2)
    void testPostUsingJSON()
    {
        JSONObject data = new JSONObject();
        data.put("name","Sun");
        data.put("location","france");
        data.put("phone","12345");

        String courseArr[] = {"C","C++"};
        data.put("courses",courseArr);

        given()
                .contentType("application/json")
                .body(data.toString()) /* In JSON we have to convert data in to string*/

                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Sun"))
                .body("location",equalTo("france"))
                .body("phone",equalTo("12345"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("content-Type","application/json")
                .log().all();
    }

    /* Using POJO(Plain Old Java Object Class) class
    here we use Encapsultion: wrapping up variables,methods in to single class
    we use getters and setters to generate or create data
    For every variable there is getter and setter
    Setter:it will take one parameter and assign the value to the class variable
    getter:Get the value from the variable
     */

    @Test(priority = 3)
    void testPostUsingPOJO()
    {

        pojo_postRequest pojo = new pojo_postRequest();
        pojo.setName("Sand");
        pojo.setLocation("India");
        pojo.setPhone("239023920");
        String coursesArr[] = {"C","C++"};
        pojo.setCourses(coursesArr);

        given()
                .contentType("application/json")
                .body(pojo)

                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Sand"))
                .body("location",equalTo("India"))
                .body("phone",equalTo("239023920"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("content-Type","application/json")
                .log().all();
    }

    /* Using External JSON file

*/
    @Test(priority = 4)
    void testPostUsingExternalJsonFile() throws FileNotFoundException {

        File f =new File(".\\bosy.json");   /* here . represents current project location*/
        FileReader fr = new FileReader(f);  /*  */
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Jo"))
                .body("location",equalTo("india"))
                .body("phone",equalTo("1234890"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]",equalTo("Selenium"))
                .header("content-Type","application/json")
                .log().all();
    }


    //deleting student record
    @Test(priority = 2)
    void testDelete()
    {
        given()

                .when()
                .delete("http://localhost:3000/students/82aa")
                .then()
                .statusCode(200);


    }
}
