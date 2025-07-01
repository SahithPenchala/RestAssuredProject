package Day1;

/*
given()
     Content type,set cookies, add auth, add param, set headers info etc...
when()
     get,put,post,delete
then()
     validate status code,extract response,extract headers cookies & response body...
*/

/*

 get request URL : https://reqres.in/api/users?page=2

 post(Create) request URL : https://reqres.in/api/users

body:
{
    "name": "morpheus",
    "job": "leader"
}

 put(Update) request URL : https://reqres.in/api/users/2

body:
{
    "name": "morpheus",
    "job": "zion resident"
}

 delete request URL : https://reqres.in/api/users/userid

 */

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class hTTPRequests {

    int id;
    @Test(priority =1)
    void getUsers()
    {
        given()
                .when()
                     .get("https://reqres.in/api/users?page=2") /* getting all users */
                .then()
                     .statusCode(200)
                     .body("page",equalTo(2))  /* response validation */
                     .log().all();  /* it will print all logs in console */
    }


    @Test(priority = 2)
    void createUser()
    {
        HashMap data = new HashMap();
        data.put("name","pavan");
        data.put("job","trainer");

        id=given()
                .header("x-api-key","reqres-free-v1")
                .contentType("application/json")
                .body(data)

                .when()
                     .post("https://reqres.in/api/users")
                     .jsonPath().getInt("id"); /* Capturing ID from user Created request*/
//                .then()
//                     .statusCode(201)
//                     .log().all();
    }

    @Test(priority = 3,dependsOnMethods = {"createUser"})
    void updateUser()
    {
        HashMap data = new HashMap();
        data.put("name","lad");
        data.put("job","teacher");

        given()
                .header("x-api-key","reqres-free-v1")
                .contentType("application/json")
                .body(data)

                .when()
                .put("https://reqres.in/api/users/"+id)
                 /* Capturing ID from user Created request*/
                .then()
                     .statusCode(200)
                     .log().all();

    }

    @Test(priority = 4)
    void deleteUser()
    {
        given()
                .header("x-api-key","reqres-free-v1")
                .when()
                .delete(" https://reqres.in/api/users/"+id)

                .then()
                .statusCode(204)
                .log().all();

    }

    @Test
    void createSingleUser()
    {
        HashMap data = new HashMap();
        data.put("name","pan");
        data.put("job","worker");

        given()
                .header("x-api-key","reqres-free-v1")
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    void getSingleUser()
    {
        given()
                .header("x-api-key","reqres-free-v1")
                .when()
                .get("https://reqres.in/api/users/7")
                .then()
                .statusCode(200)
                .log().all();

    }

}
