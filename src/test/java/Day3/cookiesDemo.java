package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

//Value of the cookies are dynamic(it will change everytime). we will check whether cookie is present or not
public class cookiesDemo {

   // @Test(priority = 1)
    void testCookies()
    {
        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC","AVh_V2h9xXa1N9FHV8T8WV4ZLvYpVeaYUABt1jk5taTtGJJ1TfvKOgLtJA")
                .log().all();

    }

    @Test(priority = 2)
    void getCookiesInfo()
    {
       Response res =given()   //it will store entire response in res variable

                .when()
                .get("https://www.google.com/");

       //get single cookie info

        String cookie_value = res.getCookie("AEC");
        System.out.println("Value of cookie is ====>"+cookie_value);

        //get all cookies info

        Map<String,String> cookies_values = res.getCookies(); //we use map because strings are in key and value pair

        //System.out.println(cookies_values.keySet()); //it will print only keys of the cookies not values....if we kows keys we can extract value like above single cookie

        for (String k:cookies_values.keySet())
                {
                    String cookie_valuee = res.getCookie(k);
                    System.out.println(k+"     "+cookie_valuee);
                }


    }
}
