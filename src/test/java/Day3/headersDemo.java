package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class headersDemo {

    private static final Logger log = LoggerFactory.getLogger(headersDemo.class);

    // @Test(priority = 1)
    void testHeaders()    //this is about checking given content is correct or not
    {
        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .header("content-type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws")
                .log().all();

    }

    @Test(priority = 2)      //here just getting the information of the headers
    void getHeaders()
    {
       Response res = given()

                .when()
                .get("https://www.google.com/");

        //get single header info

        String headerValue = res.getHeader("content-type");
        System.out.println("The Value of Content-type header is:"+headerValue);

        //Get all headers info

        // Header : it represents single header like name and value
        //Headers : Multiple headers and values

        Headers myheaders = res.getHeaders();     // this is not hashmap but will store the data in key and value pair

        for(Header hd :myheaders)
        {
            System.out.println(hd.getName()+":   "+hd.getValue());   //this is not much useful because we can get all header,values from log.all
        }


    }




}
