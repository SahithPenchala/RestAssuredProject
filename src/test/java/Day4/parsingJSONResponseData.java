package Day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;


public class parsingJSONResponseData {

    //@Test(priority = 1)
    void testJSONResponse()
    {
      //Approach 1 : if we want to validate small fiels(specific field from the JSON),status codes etc

       /* given()
                .contentType(ContentType.JSON)
                .when()

                .get("http://localhost:3000/store")

                .then()
                .statusCode(200)          // these assertions comes from import static  org.hamcrest.Matchers.* library
                .header("Content-Type","application/json")
                .body("book[3].title",equalTo("Harry Potter and the Sorcerer's Stone"))  //we can write multiple bodies in this way
                .log().all();*/

        //Approach 2 :
        Response res = given()

                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/store");

        Assert.assertEquals(res.getStatusCode(),200);   //here we use testng validation
        Assert.assertEquals(res.header("Content-Type"),"application/json");

       String bookName = res.jsonPath().get("book[3].title").toString();

       Assert.assertEquals(bookName,"Harry Potter and the Sorcerer's Stone");
    }

    @Test(priority = 2)
    void testJSONResponseBodyData()
    {

        Response res = given()

                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/store");

      /*  Assert.assertEquals(res.getStatusCode(),200);   //here we use testng validation
        Assert.assertEquals(res.header("Content-Type"),"application/json");

        String bookName = res.jsonPath().get("book[3].title").toString();

        Assert.assertEquals(bookName,"Harry Potter and the Sorcerer's Stone");  */

        //Scenario: From Each object we want to capture the title and print those titles
        //above response is in object state..we have to convert in to string format and pass into json object

        //JSON OBject Class

        JSONObject jo = new JSONObject(res.getBody().asString());   //Converting response to json object type
       //print all books
        for (int i=0;i< jo.getJSONArray("book").length();i++)
        {
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();   // this will return the value of all book titles present in book store
            System.out.println(bookTitle);
        }
        //print specific book based on title

        boolean status = false;
        for (int i=0;i< jo.getJSONArray("book").length();i++)
        {
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();   // this will return the value of all book titles present in book store
            if(bookTitle.equals("Harry Potter and the Sorcerer's Stone"))
            {
                status = true;
                break;
            }
        }

        Assert.assertEquals(status,true);


        //Add all the prices of the books and compare total price into expected total price

        double totalPrice = 0;
        for (int i=0;i< jo.getJSONArray("book").length();i++) {
            String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();   // this will return the value of all book titles present in book store

            totalPrice = totalPrice + Double.parseDouble(price); //it will convert string in to double(Wrapper class)
        }
        System.out.println("total price of books is:" + totalPrice);
        Assert.assertEquals(totalPrice,876.25);

    }
}
