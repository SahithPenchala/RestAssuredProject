package Day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;


//http://the-internet.herokuapp.com/basic_auth

public class Authentications {

    @Test(priority = 1)
    void testBasicAuthentication()
    {
        given()
                .auth().basic("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 2)
    void testDigestAuthentication()
    {
        given()
                .auth().digest("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 3)
    void testPreemptiveAuthentication()
    {
        given()
                .auth().preemptive().basic("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 4)
    void testBearerTokenAuthentication()
    {
        String bearerToken = "";
        given()
                .headers("Authorization","Bearer "+bearerToken)//this authentication we have to specify as part of Header
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
//                .body("name",equalTo("<[RestAssuredProject]>"))
                .log().all();
    }

    @Test(priority = 5)
    void testOAUTH1Authentication()
    {
        given()
                .auth().oauth("consumer key","consumer secret","access token", "token secret")  // this is for oauth 1.0 authentication
                .when()
                .get("URL")
                .then()
                .statusCode(200)
                .log().all();
    }


    @Test(priority = 6)
    void testOAUTH2Authentication()
    {
        given()
                .auth().oauth2("give outh 2 token here")  // to get outh 2 for github u have to create an app and registered to github
                .when()
                .get("URL")
                .then()
                .statusCode(200)
                .log().all();
    }

    //0ede5da03a8d07ca93f21f601a93864d  -this is the api key generated from open whether website
    @Test(priority = 7)
    void testAPIAuthentication()
    {
        /* // method 1 :here we are passing entire path and query param in URL
        given()
                .queryParam("Key name","Value here")  // here key name means API key
                .when()
                .get("URL")
                .then()
                .statusCode(200)
                .log().all();

         */

        //method 2:

        given()
                .queryParam("Key name","Value here")

                .pathParam("Path name","path here")  //here path name can be anything

                .queryParam("q","value here")  //after question mark what ever it comes we call queary param
                //etc etc

                .when()

                .get("here we hve to give only domin/{path name}") //here path parameter we have to give in get but quert para it will go as above

                .then()
                .statusCode(200)
                .log().all();
    }


}
